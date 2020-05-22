package com.backend.agendacrista.demo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;

@Entity
public class Perfil extends AbstractEntity implements GrantedAuthority {
    private String nome;

    public Perfil() {
    }

    public Perfil(Long id,String nome) {
        this.setId(id);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getAuthority() {
        return this.nome;
    }
}
