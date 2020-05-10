package com.backend.agendacrista.demo.controller;

import com.backend.agendacrista.demo.config.security.TokenService;
import com.backend.agendacrista.demo.controller.form.RegisterForm;
import com.backend.agendacrista.demo.model.Usuario;
import com.backend.agendacrista.demo.repository.UsuarioRepository;
import com.backend.agendacrista.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/register")
public class RegistrarController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private MailService mailService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registrar(@RequestBody @Valid RegisterForm form,
                                       UriComponentsBuilder uriComponentsBuilder) {

        Optional<Usuario> existeUsuario = usuarioRepository.findByEmail(form.getEmail());

        if (existeUsuario.isPresent()) {
            if (!existeUsuario.get().isEnabled()) {
                enviaEmailConfirmacao(form.converter(), existeUsuario.get(), uriComponentsBuilder);
                return ResponseEntity.badRequest()
                        .body("Email de confirmação pendende, enviamos outro para confirmação.");
            }
            return ResponseEntity.badRequest().body("Email esta sendo usado.");
        }

        Usuario save = usuarioRepository.save(new Usuario(form));
        enviaEmailConfirmacao(form.converter(), save, uriComponentsBuilder);
        return ResponseEntity.ok("Verifique sua caixa de email para confirmar sua conta.");
    }

    @GetMapping("/confirm")
    @Transactional
    public ResponseEntity<?> confirarEmail(@RequestParam(required = true) String token) {
        if (tokenService.isTokenConfirmEmailValido(token)) {
            try {
                Long id = tokenService.getIdUsuario(token);
                Usuario usuario = usuarioRepository.getOne(id);
                usuario.setAtivo(true);
                usuario.setVerificadoEm(LocalDateTime.now());
                return ResponseEntity.ok("<h1>Conta verificada com sucesso, faço o login no aplicativo</h1>");
            } catch (Exception ignored) { }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("<h1>Verificação de email sem êxito. Faça login e, " +
                        "a partir daí, solicite um novo e-mail de verificação.</h1>");
    }

    public void enviaEmailConfirmacao(UsernamePasswordAuthenticationToken dadosLogin,
                                      Usuario usuario,
                                      UriComponentsBuilder uriComponentsBuilder) {
        usuario.setAtivo(true);
        Authentication authentication = authManager.authenticate(dadosLogin);
        String token = tokenService.gerarTokenConfirmEmail(authentication);
        String url = uriComponentsBuilder.path("/register/confirm")
                .queryParam("token={token}")
                .buildAndExpand(token).toString();
        usuario.setAtivo(false);
        mailService.sendEmail(usuario, "Confime seu endereço de e-mail",
                "Para utilizar o aplicativo Agenda Cristã, " +
                        "confirme seu endereço de e-mail com o link abaixo:\n" + url);
    }
}
