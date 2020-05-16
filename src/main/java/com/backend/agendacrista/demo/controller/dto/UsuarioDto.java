package com.backend.agendacrista.demo.controller.dto;

import com.backend.agendacrista.demo.model.Usuario;

public class UsuarioDto {
    private Long id;
    private String nome;
    private String email;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

}
