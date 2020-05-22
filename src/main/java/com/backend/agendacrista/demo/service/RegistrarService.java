package com.backend.agendacrista.demo.service;

import com.backend.agendacrista.demo.config.security.TokenService;
import com.backend.agendacrista.demo.controller.form.RegisterForm;
import com.backend.agendacrista.demo.error.EmailConfirmacaoPendenteException;
import com.backend.agendacrista.demo.error.UsuarioEmailEmUsoException;
import com.backend.agendacrista.demo.model.Usuario;
import com.backend.agendacrista.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RegistrarService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    TokenService tokenService;

    public void validaERegistraEmail(RegisterForm registerForm, UriComponentsBuilder uriComponentsBuilder) {
        String email = registerForm.getEmail();
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent()) {
            if (usuario.get().isEnabled())
                throw new UsuarioEmailEmUsoException("Email em uso, faço o login");
            enviaEmailConfirmacao(registerForm.converteRegisterFormParaUsernamePasswordToken(), usuario.get(), uriComponentsBuilder);
            throw new EmailConfirmacaoPendenteException("Email de confirmação pendente, enviamos outro para sua caixa de email, sua senha sera a primeira registrada");
        }
        enviaEmailConfirmacao(registerForm.converteRegisterFormParaUsernamePasswordToken(), usuarioRepository.save(new Usuario(registerForm)), uriComponentsBuilder);
    }

    public void enviaEmailConfirmacao(UsernamePasswordAuthenticationToken dadosLogin, Usuario usuario, UriComponentsBuilder uriComponentsBuilder) {
        usuario.setAtivo(true);
        Authentication authentication = authManager.authenticate(dadosLogin);
        String token = tokenService.gerarTokenConfirmEmail(authentication);
        usuario.setAtivo(false);
        String url = uriComponentsBuilder.path("/register/confirm")
                .queryParam("token={token}")
                .buildAndExpand(token).toString();
        mailService.sendEmail(usuario, "Confime seu endereço de e-mail",
                "Para utilizar o aplicativo Agenda Cristã, " +
                        "confirme seu endereço de e-mail com o link abaixo:\n" + url);
    }

    public boolean tokenConfirmacaoEValido(String token) {
        if (tokenService.isTokenConfirmEmailValido(token)) {
            Long id = tokenService.getIdUsuario(token);
            Usuario usuario = usuarioRepository.getOne(id);
            usuario.setAtivo(true);
            usuario.setVerificadoEm(LocalDateTime.now());
            return true;
        }
        return false;
    }

}
