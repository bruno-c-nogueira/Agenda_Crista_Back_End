package com.backend.agendacrista.demo.error;

import org.springframework.security.core.AuthenticationException;

public class UsuarioEmailEmUsoException extends AuthenticationException {
    public UsuarioEmailEmUsoException(String message) {
        super(message);
    }
}
