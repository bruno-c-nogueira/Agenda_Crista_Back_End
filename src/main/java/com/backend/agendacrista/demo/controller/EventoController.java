package com.backend.agendacrista.demo.controller;

import com.backend.agendacrista.demo.controller.dto.DetalharEventoDto;
import com.backend.agendacrista.demo.controller.dto.EventoDto;
import com.backend.agendacrista.demo.controller.form.EventoForm;
import com.backend.agendacrista.demo.model.DiaDaSemana;
import com.backend.agendacrista.demo.model.Evento;
import com.backend.agendacrista.demo.repository.EventoRepository;
import com.backend.agendacrista.demo.service.EventoService;
import com.backend.agendacrista.demo.service.IgrejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/eventos")
public class EventoController {
    @Autowired
    EventoRepository eventoRepository;
    @Autowired
    EventoService eventoService;
    @Autowired
    IgrejaService igrejaService;

    @GetMapping
    public Page<EventoDto> listar(Pageable pageable) {
        return EventoDto.converteEventoPageParaEventoDtoPage(eventoRepository.findAll(pageable));
    }

    @GetMapping("/ativo/igreja/{igrejaId}")
    public ResponseEntity<List<DetalharEventoDto>> listarEventoAtivoPorIgreja(@PathVariable Long igrejaId) {
        List<Evento> listEvento = eventoRepository.findAllByIgrejaIdIs(LocalDate.now(), igrejaId);
        return ResponseEntity.ok(DetalharEventoDto.converteEventoListParaDetalharEventoDto(listEvento));
    }

    @GetMapping("/finalizado/igreja/{igrejaId}")
    public ResponseEntity<List<DetalharEventoDto>> listarEventoFinalizadoPorIgreja(@PathVariable Long igrejaId) {
        List<Evento> listEvento = eventoRepository.findByDataFinalLessThanEqualAndIgrejaId(LocalDate.now(), igrejaId);
        return ResponseEntity.ok(DetalharEventoDto.converteEventoListParaDetalharEventoDto(listEvento));
    }

    @GetMapping("/ativo/igreja/{igrejaId}/dia-semana/{diaDaSemana}")
    public ResponseEntity<List<DetalharEventoDto>> listarEventoAtivoPorIgrejaEDiaDaSemana(@PathVariable Long igrejaId, @PathVariable DiaDaSemana diaDaSemana) {
        List<Evento> listEvento = eventoRepository.findByDataFinalGreaterThanEqualOrDataFinalNullAndIgrejaIdAndHorariosDiaDaSemana(LocalDate.now(), igrejaId, diaDaSemana);
        return ResponseEntity.ok(DetalharEventoDto.converteEventoListParaDetalharEventoDto(listEvento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharEventoDto> detalhar(@PathVariable Long id) {
        eventoService.verificaSeIdEventoExiste(id);
        return ResponseEntity.ok(new DetalharEventoDto(eventoRepository.getOne(id)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalharEventoDto> deletar(@PathVariable Long id) {
        eventoService.verificaSeIdEventoExiste(id);
        igrejaService.verificaSeUsuarioLogadoAutorIgreja(eventoRepository.getOne(id).getIgreja().getId());
        eventoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid EventoForm form,
                                       UriComponentsBuilder uriComponentsBuilder) {
        igrejaService.verificaSeIdIgrejaExiste(form.getIgreja_id());
        igrejaService.verificaSeUsuarioLogadoAutorIgreja(form.getIgreja_id());
        Evento evento = eventoRepository.save(form.converteEventoFormParaEvento());
        return ResponseEntity.created(uriComponentsBuilder.path("/eventos/{id}").buildAndExpand(evento.getId()).toUri()).body(new EventoDto(evento));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid EventoForm form) {
        eventoService.verificaSeIdEventoExiste(id);
        igrejaService.verificaSeIdIgrejaExiste(form.getIgreja_id());
        igrejaService.verificaSeUsuarioLogadoAutorIgreja(form.getIgreja_id());
        return ResponseEntity.ok(new DetalharEventoDto(eventoService.atualizaEvento(id, form)));
    }
}
