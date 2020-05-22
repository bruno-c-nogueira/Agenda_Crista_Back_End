package com.backend.agendacrista.demo.error;

public class UserPricipalNotAutorizedException extends RuntimeException {
    public UserPricipalNotAutorizedException(String message) {
        super(message);
    }
}
