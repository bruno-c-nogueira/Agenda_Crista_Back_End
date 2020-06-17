package com.backend.agendacrista.demo.controller;

import com.backend.agendacrista.demo.model.Campanha;
import com.backend.agendacrista.demo.repository.CampanhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/campanha")
public class CampanhaController {

    @Autowired
    CampanhaRepository repository;

    @GetMapping
    public ResponseEntity<?> getCampanha() {
        List<Campanha> campanha = repository.findAll();
        return ResponseEntity.ok(campanha);
    }

    @GetMapping
    public ResponseEntity<?> postCampanha() {
        List<Campanha> campanha = repository.findAll();
        return ResponseEntity.ok(campanha);
    }

}
