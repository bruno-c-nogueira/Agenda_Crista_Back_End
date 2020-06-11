package com.backend.agendacrista.demo.error;

import org.springframework.security.core.AuthenticationException;

public class TokenExpiradoException extends AuthenticationException {
    public TokenExpiradoException(String message) {
        super(message);
    }
}
