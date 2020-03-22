package com.backend.agendacrista.demo.controller;

import com.backend.agendacrista.demo.controller.dto.NoticiaDto;
import com.backend.agendacrista.demo.controller.form.AtualizacaoNoticiaForm;
import com.backend.agendacrista.demo.controller.form.NoticiaForm;
import com.backend.agendacrista.demo.model.Noticia;
import com.backend.agendacrista.demo.repository.NoticiaRepository;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/noticias")
public class NoticiasController {

    @Autowired
    private NoticiaRepository noticiaRepository;

    //Paginacao por data de criacao
    @GetMapping
    public Page<NoticiaDto> listaPage(@PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC)Pageable pageable){
        Page<Noticia> noticias = noticiaRepository.findAll(pageable);
        return NoticiaDto.converter(noticias);
    }

    @PostMapping
    public ResponseEntity<NoticiaDto>cadastrar(@RequestBody @Valid NoticiaForm noticiaForm, UriComponentsBuilder uriComponentsBuilder){
        Noticia noticia = noticiaForm.converter(noticiaRepository);
        noticiaRepository.save(noticia);
        URI uri = uriComponentsBuilder.path("/noticias/{id}").buildAndExpand(noticia.getId()).toUri();
        return ResponseEntity.created(uri).body(new NoticiaDto(noticia));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Noticia> optionalTopico = noticiaRepository.findById(id);
        if (optionalTopico.isPresent()) {
            noticiaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<NoticiaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoNoticiaForm form) {
        Optional<Noticia> optionalTopico = noticiaRepository.findById(id);
        if (optionalTopico.isPresent()) {
            Noticia topico = form.atualizar(id, noticiaRepository);
            return ResponseEntity.ok(new NoticiaDto(topico));
        }

        return ResponseEntity.notFound().build();
    }
}
