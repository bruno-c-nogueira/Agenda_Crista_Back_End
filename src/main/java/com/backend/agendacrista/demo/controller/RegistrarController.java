package com.backend.agendacrista.demo.controller;

import com.backend.agendacrista.demo.controller.form.RegisterForm;
import com.backend.agendacrista.demo.service.RegistrarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
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

    @GetMapping("/confirm")
    @Transactional
    public ResponseEntity<?> confirarEmail(@RequestParam(required = true) String token) {
        return registrarService.tokenConfirmacaoEValido(token) ? ResponseEntity.ok("<h1>Conta verificada com sucesso, faço o login no aplicativo</h1>") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("<h1>Verificação de email sem êxito. Faça login e, " +
                        "a partir daí, solicite um novo e-mail de verificação.</h1>");
    }
}
