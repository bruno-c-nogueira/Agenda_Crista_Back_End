package com.backend.agendacrista.demo.controller.form;

import javax.validation.constraints.Email;

public class RecuperarSenhaEmailForm {

    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
