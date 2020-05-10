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

    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    @Value("${forum.jwt.issuer.token_login}")
    private String issuerTokenLogin;

    @Value("${forum.jwt.issuer.token_confirm_email}")
    private String issuerTokenConfirmEmail;

    public String gerarTokenLogin(Authentication authentication) {
        return this.gerarToken(authentication, issuerTokenLogin);
    }

    public String gerarTokenConfirmEmail(Authentication authentication) {
        return this.gerarToken(authentication, issuerTokenConfirmEmail);
    }

    public String gerarToken(Authentication authentication, String issuer) {
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(usuarioLogado.getId().toString())
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
