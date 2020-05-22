package com.backend.agendacrista.demo.controller;


import com.backend.agendacrista.demo.controller.dto.CidadeDto;
import com.backend.agendacrista.demo.controller.dto.EstadoDto;
import com.backend.agendacrista.demo.repository.CidadeRepository;
import com.backend.agendacrista.demo.repository.EstadoRepository;
import com.backend.agendacrista.demo.service.LocalidadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/localidades")
public class LocalidadesController {

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    CidadeRepository cidadeRepository;
    @Autowired
    LocalidadesService localidadesService;

    @GetMapping("/estados")
    @Cacheable(value = "estados")
    public ResponseEntity<List<EstadoDto>> estados() {
        return ResponseEntity.ok(EstadoDto.converteEstadoListParaEstadoDtoList(estadoRepository.findAll()));
    }

    @GetMapping("/{uf}/{iniCidade}")
    @Cacheable(value = "estados")
    public ResponseEntity<List<?>> cidadeByLetra(@PathVariable String iniCidade, @PathVariable String uf) {
        localidadesService.verificaSeUfExiste(uf);
        return ResponseEntity.ok(CidadeDto.converteCidadeListParaCidadeDtoList(cidadeRepository.findAllByNomeIsStartingWithAndUfUfContains(iniCidade, uf)));
    }

    @GetMapping("/estados/{uf}")
    @Cacheable(value = "estados-cidades")
    public ResponseEntity<List<CidadeDto>> estadoCidade(@PathVariable String uf) {
        localidadesService.verificaSeUfExiste(uf);
        return ResponseEntity.ok(CidadeDto.converteCidadeListParaCidadeDtoList(cidadeRepository.findByUfUf(uf)));
    }


}
