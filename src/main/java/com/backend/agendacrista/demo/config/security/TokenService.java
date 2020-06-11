package com.backend.agendacrista.demo.config.security;

import com.backend.agendacrista.demo.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${agenda.jwt.expiration}")
    private String expiration;

    @Value("${agenda.jwt.secret}")
    private String secret;

    private final String issuerTokenLogin = "login";
    private final String issuerTokenConfirmEmail = "email";
    private final String issuuerTokenReuperarSenha = "recovery-password";

    public String gerarTokenLogin(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return this.gerarToken(usuario, issuerTokenLogin, this.expiration);
    }

    public String gerarTokenConfirmEmail(Usuario usuario) {
        return this.gerarToken(usuario, issuerTokenConfirmEmail, this.expiration);
    }

    public String gerarTokenRecuperarSenha(Usuario usuario) {
        return this.gerarToken(usuario, issuuerTokenReuperarSenha, this.expiration);
    }

    public String gerarToken(Usuario usuario, String issuer, String expiration) {
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(usuario.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenLoginValido(String token) {
        return this.isTokenValido(token, issuerTokenLogin);
    }

    public boolean isTokenConfirmEmailValido(String token) {
        return this.isTokenValido(token, issuerTokenConfirmEmail);
    }

    public boolean isTokenRecuperarSenhaValido(String token) {
        return this.isTokenValido(token, issuuerTokenReuperarSenha);
    }

    public boolean isTokenValido(String token, String issuer) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getIssuer().equals(issuer);
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(body.getSubject());
    }
}
