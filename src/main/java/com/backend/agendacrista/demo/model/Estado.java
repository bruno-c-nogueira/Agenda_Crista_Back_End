package com.backend.agendacrista.demo.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Estado extends AbstractEntity {
    private String nome;
    private String uf;
    private Integer ibge;
    @ManyToOne
    private Pais pais;
    private String ddd;

    private String imagem_estado;

    public String getImagem_estado() {
        return imagem_estado;
    }

    public void setImagem_estado(String imagem_estado) {
        this.imagem_estado = imagem_estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Integer getIbge() {
        return ibge;
    }

    public void setIbge(Integer ibge) {
        this.ibge = ibge;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }
}
