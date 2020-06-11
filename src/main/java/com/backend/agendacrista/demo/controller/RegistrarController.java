package com.backend.agendacrista.demo.controller;

import com.backend.agendacrista.demo.controller.form.RegisterForm;
import com.backend.agendacrista.demo.service.RegistrarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegistrarController {

    @Autowired
    private RegistrarService registrarService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registrar(@RequestBody @Valid RegisterForm form,
                                       UriComponentsBuilder uriComponentsBuilder) {
        registrarService.validaERegistraEmail(form, uriComponentsBuilder);
        return ResponseEntity.ok().build();
    }

}
