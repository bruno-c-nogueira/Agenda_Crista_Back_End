package com.backend.agendacrista.demo.controller;

import com.backend.agendacrista.demo.config.security.TokenService;
import com.backend.agendacrista.demo.controller.dto.TokenDto;
import com.backend.agendacrista.demo.controller.dto.UsuarioDto;
import com.backend.agendacrista.demo.controller.form.LoginForm;
import com.backend.agendacrista.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<Object> autenticar(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        Authentication authentication = authManager.authenticate(dadosLogin);
        String token = tokenService.gerarTokenLogin(authentication);
        Usuario userDetail = (Usuario) authentication.getPrincipal();
        return ResponseEntity.ok(new TokenDto(token, "Bearer", new UsuarioDto(userDetail)));
    }
}
