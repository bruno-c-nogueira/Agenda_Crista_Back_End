package com.backend.agendacrista.demo.controller;

import com.backend.agendacrista.demo.controller.dto.DetalharEventoDto;
import com.backend.agendacrista.demo.controller.dto.EventoDto;
import com.backend.agendacrista.demo.controller.form.AtualizaEventoForm;
import com.backend.agendacrista.demo.controller.form.EventoForm;
import com.backend.agendacrista.demo.model.Cidade;
import com.backend.agendacrista.demo.model.Evento;
import com.backend.agendacrista.demo.model.Igreja;
import com.backend.agendacrista.demo.model.Usuario;
import com.backend.agendacrista.demo.repository.CidadeRepository;
import com.backend.agendacrista.demo.repository.EventoRepository;
import com.backend.agendacrista.demo.repository.IgrejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    IgrejaRepository igrejaRepository;

    @GetMapping
    public Page<EventoDto> listar(@RequestParam(required = false) Long igreja_id,
                                  @RequestParam(required = false) Integer cidade_id,
                                  Pageable pageable) {
        Page<Evento> eventos;
        if (cidade_id != null)
            eventos = eventoRepository.findByCidadeId(cidade_id, pageable);
        else if (igreja_id != null)
            eventos = eventoRepository.findByIgrejaId(igreja_id, pageable);
        else
            eventos = eventoRepository.findAll(pageable);
        return EventoDto.converte(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharEventoDto> detalhar(@PathVariable Long id) {

        Optional<Evento> evento = eventoRepository.findById(id);
        if (evento.isPresent()) {
            return ResponseEntity.ok(new DetalharEventoDto(evento.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalharEventoDto> deletar(@PathVariable Long id) {

        Optional<Evento> evento = eventoRepository.findById(id);
        if (evento.isPresent() && IgrejaController.getIdUsuarioLogado() == evento.get().getUsuario().getId()) {
            eventoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid EventoForm form,
                                               UriComponentsBuilder uriComponentsBuilder) {

        Optional<Cidade> cidade = cidadeRepository.findById(form.getCidade_id());
        Optional<Igreja> igreja = igrejaRepository.findById(form.getIgreja_id());

        if (cidade.isPresent() && igreja.isPresent()) {

            if (igreja.get().getUsuario().getId() != IgrejaController.getIdUsuarioLogado())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("usuário não tem permissão de criar evento para essa igreja");

            Evento evento = new Evento(form, cidade.get(), igreja.get(), new Usuario(IgrejaController.getIdUsuarioLogado()));
            eventoRepository.save(evento);
            URI uri = uriComponentsBuilder.path("/eventos/{id}").buildAndExpand(evento.getId()).toUri();
            return ResponseEntity.created(uri).body(new EventoDto(evento));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id da cidade ou igreja inváidos");

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaEventoForm form) {
        Optional<Evento> evento = eventoRepository.findById(id);
        if (evento.isPresent()) {
            if (evento.get().getUsuario().getId() != IgrejaController.getIdUsuarioLogado())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("usuário não tem permissão editar esse evento");
            return ResponseEntity.ok(new DetalharEventoDto(form.converte(id, eventoRepository)));
        }
        return ResponseEntity.notFound().build();
    }






}
