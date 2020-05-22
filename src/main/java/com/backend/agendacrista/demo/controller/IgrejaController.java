package com.backend.agendacrista.demo.controller;

import com.backend.agendacrista.demo.controller.dto.DetalharIgrejaDto;
import com.backend.agendacrista.demo.controller.dto.IgrejaDto;
import com.backend.agendacrista.demo.controller.form.AtualizaIgrejaForm;
import com.backend.agendacrista.demo.controller.form.IgrejaForm;
import com.backend.agendacrista.demo.error.ResourceNotFoundException;
import com.backend.agendacrista.demo.model.Igreja;
import com.backend.agendacrista.demo.model.StatusIgreja;
import com.backend.agendacrista.demo.model.Usuario;
import com.backend.agendacrista.demo.repository.CidadeRepository;
import com.backend.agendacrista.demo.repository.IgrejaRepository;
import com.backend.agendacrista.demo.service.LocalidadesService;
import com.backend.agendacrista.demo.service.IgrejaService;
import com.backend.agendacrista.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/igrejas")
public class IgrejaController {

    @Autowired
    private IgrejaRepository igrejaRepository;
    @Autowired
    private IgrejaService igrejaService;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private LocalidadesService localidadesService;

    @GetMapping
    public Page<IgrejaDto> listar(Pageable pageable) {
        return IgrejaDto.converteIgrejaPageParaIgrejaDtoPage(igrejaRepository.findByStatusIgreja(StatusIgreja.VERIFICADO, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharIgrejaDto> detalhar(@PathVariable Long id) {
        igrejaService.verificaSeIdIgrejaExiste(id);
        return ResponseEntity.ok(new DetalharIgrejaDto(igrejaRepository.getOne(id)));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid IgrejaForm form, UriComponentsBuilder uriComponentsBuilder) {
        localidadesService.verificaSeIdCidadeExiste(form.getEndereco().getCidade_id());
        Igreja igreja = form.converteIgrejaFormParaIgreja();
        igreja = igrejaRepository.save(igreja);
        return ResponseEntity.created(uriComponentsBuilder.path("/igrejas/{id}").buildAndExpand(igreja.getId()).toUri()).body(new IgrejaDto(igreja));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        igrejaService.verificaSeIdIgrejaExiste(id);
        igrejaService.verificaSeUsuarioLogadoAutorIgreja(id);
        igrejaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalharIgrejaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaIgrejaForm atualizaIgrejaForm) {
        igrejaService.verificaSeIdIgrejaExiste(id);
        igrejaService.verificaSeUsuarioLogadoAutorIgreja(id);
        return ResponseEntity.ok(new DetalharIgrejaDto(atualizaIgrejaForm.atualizaIgrejaFormParaIgreja(id, igrejaRepository, cidadeRepository)));
    }

    @GetMapping("/user")
    public ResponseEntity<List<DetalharIgrejaDto>> igrejasPorUsuarioLogado() {
        List<Igreja> igrejas = igrejaRepository.findByUsuario(new Usuario(UserService.getIdUsuarioLogado()));
        if (igrejas.isEmpty()) {
            throw new ResourceNotFoundException("Usuario não tem igrejas");
        }
        return ResponseEntity.ok(DetalharIgrejaDto.converteIgrejaListParaIgrejaDtoList(igrejas));
    }

}
