package com.backend.agendacrista.demo.controller;

import com.backend.agendacrista.demo.controller.dto.DetalharIgrejaDto;
import com.backend.agendacrista.demo.controller.dto.IgrejaDto;
import com.backend.agendacrista.demo.controller.form.AtualizaIgrejaForm;
import com.backend.agendacrista.demo.controller.form.IgrejaForm;
import com.backend.agendacrista.demo.model.*;
import com.backend.agendacrista.demo.repository.CidadeRepository;
import com.backend.agendacrista.demo.repository.EnderecoRepository;
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

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    public Page<IgrejaDto> listar(@RequestParam(required = false) String estado, @RequestParam(required = false) Integer cidadeId, Pageable pageable) {

        Page<Igreja> igrejas;

        if (estado != null)
            igrejas = igrejaRepository.findByEnderecoCidadeUfUfAndStatusIgreja(estado, StatusIgreja.VERIFICADO, pageable);
        else if (cidadeId == null)
            igrejas = igrejaRepository.findByStatusIgreja(StatusIgreja.VERIFICADO, pageable);
        else
            igrejas = igrejaRepository.findByEnderecoCidadeIdAndStatusIgreja(cidadeId, StatusIgreja.VERIFICADO, pageable);

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

    @GetMapping("/user")
    public ResponseEntity<?> minhasIgrejas() {
        Optional<List<Igreja>> igrejas = igrejaRepository.findByUsuario(new Usuario(getIdUsuarioLogado()));
        if (igrejas.isPresent()) {
            return ResponseEntity.ok(DetalharIgrejaDto.converte(igrejas.get()));
        }
        return ResponseEntity.badRequest().body("Você não adimistra nenhuma igreja!");
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid IgrejaForm form, UriComponentsBuilder uriComponentsBuilder) {

        Optional<Cidade> cidade = cidadeRepository.findById(form.getEndereco().getCidade_id());

        if (cidade.isPresent()) {

            Endereco endereco = new Endereco(form.getEndereco(), cidade.get());
            Igreja igreja = new Igreja(form, new Usuario(getIdUsuarioLogado()), endereco);
            enderecoRepository.save(endereco);
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

        if (igreja.isPresent() && igreja.get().getUsuario().getId().equals(getIdUsuarioLogado()) ) {
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
            atualizaIgrejaForm.converte(id, igrejaRepository, cidadeRepository);
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
