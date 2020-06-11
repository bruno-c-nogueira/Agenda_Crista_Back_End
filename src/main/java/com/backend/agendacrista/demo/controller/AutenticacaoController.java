package com.backend.agendacrista.demo.controller;

import com.backend.agendacrista.demo.config.security.TokenService;
import com.backend.agendacrista.demo.controller.dto.TokenDto;
import com.backend.agendacrista.demo.controller.dto.UsuarioDto;
import com.backend.agendacrista.demo.controller.form.LoginForm;
import com.backend.agendacrista.demo.controller.form.RecuperarSenhaEmailForm;
import com.backend.agendacrista.demo.controller.form.RecuperarSenhaSenhaForm;
import com.backend.agendacrista.demo.model.Usuario;
import com.backend.agendacrista.demo.repository.UsuarioRepository;
import com.backend.agendacrista.demo.service.RegistrarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RegistrarService registrarService;

    @Transactional
    @PostMapping
    public ResponseEntity<Object> autenticar(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        Authentication authentication = authManager.authenticate(dadosLogin);
        String token = tokenService.gerarTokenLogin(authentication);
        Usuario userDetail = (Usuario) authentication.getPrincipal();
        userDetail.setTokenFcm(form.getTokenFcm());
        return ResponseEntity.ok(new TokenDto(token, "Bearer", new UsuarioDto(userDetail)));
    }

    @PostMapping("/confirm")
    @Transactional
    public ResponseEntity<?> confirarEmail(@RequestParam(required = true) String token) {
        return registrarService.tokenConfirmacaoEValido(token) ? ResponseEntity.ok("<h1>Conta verificada com sucesso, faço o login no aplicativo</h1>") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("<h1>Verificação de email sem êxito. Faça login e, " +
                        "a partir daí, solicite um novo e-mail de verificação.</h1>");
    }

    @Transactional
    @PostMapping("/resetPassword/complete")
    public ResponseEntity<?> recuperaSenha(@RequestBody @Valid RecuperarSenhaSenhaForm form) {
        registrarService.alteraSenha(form);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> enviaSenhaRecuperacaoEmail(@RequestBody @Valid RecuperarSenhaEmailForm form, UriComponentsBuilder uriComponentsBuilder) {
        registrarService.recuperarSenhaPorEmail(form, uriComponentsBuilder);
        return ResponseEntity.ok().build();
    }
}
