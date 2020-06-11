package com.backend.agendacrista.demo.controller;

import com.backend.agendacrista.demo.controller.dto.NoticiaDto;
import com.backend.agendacrista.demo.controller.form.AtualizacaoNoticiaForm;
import com.backend.agendacrista.demo.controller.form.NoticiaForm;
import com.backend.agendacrista.demo.model.Noticia;
import com.backend.agendacrista.demo.repository.NoticiaRepository;
import com.backend.agendacrista.demo.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/noticias")
public class NoticiasController {

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private NoticiaService noticiaService;

    @GetMapping
    public Page<NoticiaDto> listaPage(@PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Noticia> noticias = noticiaRepository.findAll(pageable);
        return NoticiaDto.converteNoticiaPageParaNoticiaDtoPage(noticias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticiaDto> noticiaPorId(@PathVariable Long id) {
        noticiaService.verificaSeIdNoticiaExiste(id);
        return ResponseEntity.ok(new NoticiaDto(noticiaRepository.getOne(id)));
    }

    @PostMapping
    public ResponseEntity<NoticiaDto> cadastrar(@RequestBody @Valid NoticiaForm noticiaForm, UriComponentsBuilder uriComponentsBuilder) {
        Noticia noticia = noticiaForm.converteNoticiaFormParaNoticia();
        noticiaRepository.save(noticia);
        URI uri = uriComponentsBuilder.path("/noticias/{id}").buildAndExpand(noticia.getId()).toUri();
        noticiaService.enviaNotificacao(noticia);
        return ResponseEntity.created(uri).body(new NoticiaDto(noticia));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        noticiaService.verificaSeIdNoticiaExiste(id);
        noticiaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<NoticiaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoNoticiaForm form) {
        noticiaService.verificaSeIdNoticiaExiste(id);
        Noticia topico = form.atualizar(id, noticiaRepository);
        return ResponseEntity.ok(new NoticiaDto(topico));
    }
}
