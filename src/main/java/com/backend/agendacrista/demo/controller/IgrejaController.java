package com.backend.agendacrista.demo.controller;


import com.backend.agendacrista.demo.config.security.TokenService;
import com.backend.agendacrista.demo.controller.dto.DetalharIgrejaDto;
import com.backend.agendacrista.demo.controller.dto.IgrejaDto;
import com.backend.agendacrista.demo.controller.form.AtualizaIgrejaForm;
import com.backend.agendacrista.demo.controller.form.IgrejaForm;
import com.backend.agendacrista.demo.model.Cidade;
import com.backend.agendacrista.demo.model.Igreja;
import com.backend.agendacrista.demo.model.Usuario;
import com.backend.agendacrista.demo.repository.CidadeRepository;
import com.backend.agendacrista.demo.repository.IgrejaRepository;
import com.backend.agendacrista.demo.repository.UsuarioRepository;
import org.hibernate.cfg.NotYetImplementedException;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/igrejas")
public class IgrejaController {


    @Autowired
    private IgrejaRepository igrejaRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public Page<IgrejaDto> lista(@RequestParam(required = false) String nome, Pageable pageable) {

        Page<Igreja> igrejas;

        if (nome == null)
            igrejas = igrejaRepository.findAll(pageable);
        else
            igrejas = igrejaRepository.findByNomeIgnoreCaseContaining(nome, pageable);

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
    public ResponseEntity<?> cadastrar(@RequestBody @Valid IgrejaForm form,
                                               UriComponentsBuilder uriComponentsBuilder,
                                               @RequestHeader("Authorization") String authorization) {

        Optional<Cidade> cidade = cidadeRepository.findById(form.getCidade_id().intValue());
        Usuario usuario = getUsuarioLogado(authorization);
        Igreja igreja;

        if (cidade.isPresent()) {
            igreja = new Igreja(form, cidade.get(), usuario);
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id da cidade inválido");

        igrejaRepository.save(igreja);

        URI uri = uriComponentsBuilder.path("/igrejas/{id}").buildAndExpand(igreja.getId()).toUri();
        return ResponseEntity.created(uri).body(new IgrejaDto(igreja));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id, @RequestHeader("Authorization") String authorization) {
        Optional<Igreja> igreja = igrejaRepository.findById(id);

        if (igreja.isPresent() && igreja.get().getUsuario() == getUsuarioLogado(authorization) ) {
            igrejaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaIgrejaForm atualizaIgrejaForm,
                                       @RequestHeader("Authorization") String authorization) {
        Optional<Igreja> igreja = igrejaRepository.findById(id);

        if (igreja.isPresent() && igreja.get().getUsuario() == getUsuarioLogado(authorization)) {
            Igreja igrejaForm = atualizaIgrejaForm.converte(id, igrejaRepository);
            return ResponseEntity.ok(new DetalharIgrejaDto(igreja.get()));
        }

        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/cidade/{id}")
    public ResponseEntity<?> igrejasPorCidade(@PathVariable Integer id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);
        if (cidade.isPresent()) {
            Optional<List<Igreja>> igrejas = igrejaRepository.findByCidadeId(id);
            if (igrejas.isPresent())
                return ResponseEntity.ok(IgrejaDto.converte(igrejas.get()));
        }
        return ResponseEntity.notFound().build();
    }

    public Usuario getUsuarioLogado(String authorization) {
        String token = authorization.substring(7, authorization.length());
        Long idUsuario = tokenService.getIdUsuario(token);
        return usuarioRepository.findById(idUsuario).get();

    }

}
