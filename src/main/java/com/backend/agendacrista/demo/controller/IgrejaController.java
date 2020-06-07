package com.backend.agendacrista.demo.controller;

import com.backend.agendacrista.demo.controller.dto.DetalharIgrejaDto;
import com.backend.agendacrista.demo.controller.dto.IgrejaDto;
import com.backend.agendacrista.demo.controller.form.AtualizaIgrejaForm;
import com.backend.agendacrista.demo.controller.form.IgrejaForm;
import com.backend.agendacrista.demo.model.Igreja;
import com.backend.agendacrista.demo.model.StatusIgreja;
import com.backend.agendacrista.demo.model.Usuario;
import com.backend.agendacrista.demo.repository.IgrejaRepository;
import com.backend.agendacrista.demo.service.IgrejaService;
import com.backend.agendacrista.demo.service.LocalidadesService;
import com.backend.agendacrista.demo.service.UsusarioService;
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
    private LocalidadesService localidadesService;

    @GetMapping
    public Page<IgrejaDto> listar(Pageable pageable) {
        return IgrejaDto.converteIgrejaPageParaIgrejaDtoPage(igrejaRepository.findByStatusIgreja(StatusIgreja.VERIFICADO, pageable));
    }

    @GetMapping("/admin/em-analise")
    public Page<DetalharIgrejaDto> listarEmAnalise(Pageable pageable) {
        return DetalharIgrejaDto.converteIgrejaPageParaDetalharIgrejaDtoPage(igrejaRepository.findByStatusIgreja(StatusIgreja.EM_ANALISE, pageable));
    }
    @Transactional
    @PutMapping("/admin/confirma-igreja/{id}")
    public ResponseEntity<?> confirmaStatusIgreja(@PathVariable Long id) {
        igrejaService.alteraStausIgreja(id, StatusIgreja.VERIFICADO);
        return ResponseEntity.ok().build();
    }
    @Transactional
    @PutMapping("/admin/bloqueia-igreja/{id}")
    public ResponseEntity<?> bloqueiaStatusIgreja(@PathVariable Long id) {
        igrejaService.alteraStausIgreja(id, StatusIgreja.BLOQUEADO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharIgrejaDto> detalhar(@PathVariable Long id) {
        igrejaService.verificaSeIdIgrejaExiste(id);
        return ResponseEntity.ok(new DetalharIgrejaDto(igrejaRepository.getOne(id)));
    }

    @GetMapping("/cidade/{id}")
    public ResponseEntity<List<DetalharIgrejaDto>> listarIgrejasPorCidafavoritadade(@PathVariable Long id) {
        localidadesService.verificaSeIdCidadeExiste(id);
        return ResponseEntity.ok(DetalharIgrejaDto.converteIgrejaListParaIgrejaDtoList(igrejaRepository.findByEnderecoCidadeIdAndStatusIgrejaIs(id, StatusIgreja.VERIFICADO)));
    }

    @GetMapping("/favoritas/")
    public ResponseEntity<List<DetalharIgrejaDto>> listarIgrejasFavoritasPorUsuarioLogado() {
        return ResponseEntity.ok(DetalharIgrejaDto.converteIgrejaListParaIgrejaDtoList(igrejaService.igrejasFavoritasPorUsuarioLogado()));
    }

    @GetMapping("/favoritada/{id}")
    public ResponseEntity<?> verificaIgrejaEFavoritada(@PathVariable Long id) {
        return ResponseEntity.ok(igrejaService.verificaIgrejaEFavoritada(id));
    }

    @Transactional
    @PutMapping("/favoritar/{id}")
    public ResponseEntity adicionaIgrejaFavoritaPorId(@PathVariable Long id) {
        igrejaService.verificaSeIdIgrejaExiste(id);
        igrejaService.adicionaIgrejaFavoritaPorId(id);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PutMapping("/desfavoritar/{id}")
    public ResponseEntity removeIgrejaFavoritaPorId(@PathVariable Long id) {
        igrejaService.verificaSeIdIgrejaExiste(id);
        igrejaService.removeIgrejaFavoritaPorId(id);
        return ResponseEntity.ok().build();
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
        return ResponseEntity.ok(new DetalharIgrejaDto(igrejaService.atualizaIgreja(id, atualizaIgrejaForm)));
    }

    @GetMapping("/usuario/logado")
    public ResponseEntity<List<DetalharIgrejaDto>> igrejasPorUsuarioLogado() {
        List<Igreja> igrejas = igrejaRepository.findByUsuarioOrderByNome(new Usuario(UsusarioService.getIdUsuarioLogado()));
        return ResponseEntity.ok(DetalharIgrejaDto.converteIgrejaListParaIgrejaDtoList(igrejas));
    }

}
