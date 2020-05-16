package com.backend.agendacrista.demo.controller.dto;

import com.backend.agendacrista.demo.model.Usuario;

public class TokenDto {
    private String token;
    private String tipo;
    private UsuarioDto usuario;

    public TokenDto(String token, String tipo, UsuarioDto usuario) {
        this.token = token;
        this.tipo = tipo;
        this.usuario = usuario;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }

}
