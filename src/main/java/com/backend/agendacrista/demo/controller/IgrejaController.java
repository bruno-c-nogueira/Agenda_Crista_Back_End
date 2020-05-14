package com.backend.agendacrista.demo.controller;

import com.backend.agendacrista.demo.controller.dto.DetalharIgrejaDto;
import com.backend.agendacrista.demo.controller.dto.IgrejaDto;
import com.backend.agendacrista.demo.controller.form.AtualizaIgrejaForm;
import com.backend.agendacrista.demo.controller.form.IgrejaForm;
import com.backend.agendacrista.demo.model.Cidade;
import com.backend.agendacrista.demo.model.Igreja;
import com.backend.agendacrista.demo.model.Usuario;
import com.backend.agendacrista.demo.repository.CidadeRepository;
import com.backend.agendacrista.demo.repository.IgrejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/igrejas")
public class IgrejaController {

    @Autowired
    private IgrejaRepository igrejaRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    public Page<IgrejaDto> listar(@RequestParam(required = false) Integer cidade_id, Pageable pageable) {

        Page<Igreja> igrejas;

        if (cidade_id == null)
            igrejas = igrejaRepository.findAll(pageable);
        else
            igrejas = igrejaRepository.findByEndereco_CidadeId(cidade_id, pageable);

        return IgrejaDto.converte(igrejas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharIgrejaDto> detalhar(@PathVariable Long id) {
        Optional<Igreja> igreja = igrejaRepository.findById(id);

        if (igreja.isPresent()) {
            return ResponseEntity.ok(new DetalharIgrejaDto(igreja.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid IgrejaForm form, UriComponentsBuilder uriComponentsBuilder) {

        Optional<Cidade> cidade = cidadeRepository.findById(form.getEndereco().getId());

        if (cidade.isPresent()) {
            Igreja igreja = new Igreja(form, new Usuario(getIdUsuarioLogado()));
            igrejaRepository.save(igreja);
            URI uri = uriComponentsBuilder.path("/igrejas/{id}").buildAndExpand(igreja.getId()).toUri();
            return ResponseEntity.created(uri).body(new IgrejaDto(igreja));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id da cidade inválido");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Igreja> igreja = igrejaRepository.findById(id);

        if (igreja.isPresent() && igreja.get().getUsuario().getId() == getIdUsuarioLogado() ) {
            igrejaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaIgrejaForm atualizaIgrejaForm) {
        Optional<Igreja> igreja = igrejaRepository.findById(id);

        if (igreja.isPresent() && igreja.get().getUsuario().getId().equals(getIdUsuarioLogado())) {
            atualizaIgrejaForm.converte(id, igrejaRepository);
            return ResponseEntity.ok(new DetalharIgrejaDto(igreja.get()));
        }

        return ResponseEntity.notFound().build();
    }

    public static Long getIdUsuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) auth.getPrincipal();
        return usuario.getId();
    }

}
