package com.backend.agendacrista.demo.error;

import org.springframework.security.core.AuthenticationException;

public class EmailConfirmacaoPendenteException extends AuthenticationException {
    public EmailConfirmacaoPendenteException(String msg) {
        super(msg);
    }
}
