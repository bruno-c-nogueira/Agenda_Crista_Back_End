package com.backend.agendacrista.demo.model;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Cidade extends AbstractEntity {

    private String nome;
    @ManyToOne
    private Estado uf;
    private Integer ibge;


    public Cidade() {
    }

    public Cidade(Long id) {
        this.setId(id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getUf() {
        return uf;
    }

    public void setUf(Estado uf) {
        this.uf = uf;
    }

    public Integer getIbge() {
        return ibge;
    }

    public void setIbge(Integer ibge) {
        this.ibge = ibge;
    }
}
