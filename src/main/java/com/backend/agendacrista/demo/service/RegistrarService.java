package com.backend.agendacrista.demo.service;

import com.backend.agendacrista.demo.config.security.TokenService;
import com.backend.agendacrista.demo.controller.form.RecuperarSenhaEmailForm;
import com.backend.agendacrista.demo.controller.form.RecuperarSenhaSenhaForm;
import com.backend.agendacrista.demo.controller.form.RegisterForm;
import com.backend.agendacrista.demo.error.EmailConfirmacaoPendenteException;
import com.backend.agendacrista.demo.error.TokenExpiradoException;
import com.backend.agendacrista.demo.error.UsuarioEmailEmUsoException;
import com.backend.agendacrista.demo.model.Usuario;
import com.backend.agendacrista.demo.repository.UsuarioRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
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
        String token = tokenService.gerarTokenConfirmEmail(usuario);
        String url = uriComponentsBuilder.path("/confirm-email.html")
                .queryParam("token={token}")
                .buildAndExpand(token).toString();
        mailService.sendEmail(usuario, "Confime seu endereço de e-mail",
                "Para utilizar o aplicativo Agenda Cristã, " +
                        "confirme seu endereço de e-mail com o link abaixo:\n" + url);
    }

    public boolean tokenConfirmacaoEValido(String token) {
        if (tokenService.isTokenConfirmEmailValido(token)) {
            Usuario usuario = usuarioRepository.getOne(tokenService.getIdUsuario(token));
            usuario.setAtivo(true);
            usuario.setVerificadoEm(LocalDateTime.now());
            return true;
        }
        return false;
    }

    public void enviaEmailRecuperacaoDeSenha(RecuperarSenhaEmailForm form, UriComponentsBuilder uriComponentsBuilder){
        Optional<Usuario> usuario = usuarioRepository.findByEmail(form.getEmail());
        if (usuario.isPresent()) {
            Usuario user = usuario.get();
            String token = tokenService.gerarTokenRecuperarSenha(user);
            String url = uriComponentsBuilder.path("/recover.html")
                    .queryParam("token={token}")
                    .buildAndExpand(token).toString();
            mailService.sendEmail(user, "Link para alterar a senha",
                    "Olá " + user.getNome()  + "\n" +
                            "Aparentemente, você pediu para alterar sua senha. Para fazer isso basta seguir o link::\n" + url);

        }

    }

    public void alteraSenha(RecuperarSenhaSenhaForm form) {
        String token = form.getToken();
        if (tokenService.isTokenRecuperarSenhaValido(token)){
            Usuario usuario = usuarioRepository.getOne(tokenService.getIdUsuario(token));
            usuario.setSenha(form.getPassword());
            return;
        }

        throw new TokenExpiradoException("Falha ao trocar a senha, token inválido!");
    }

}
