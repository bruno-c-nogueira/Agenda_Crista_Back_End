package com.backend.agendacrista.demo.model;

import com.backend.agendacrista.demo.controller.form.CampanhaForm;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Campanha  extends AbstractEntity{

    private String titulo;
    private String descricao;
    private Double meta;
    private Double arrecadado;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Campanha() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getMeta() {
        return meta;
    }

    public void setMeta(Double meta) {
        this.meta = meta;
    }

    public Double getArrecadado() {
        return arrecadado;
    }

    public void setArrecadado(Double arrecadado) {
        this.arrecadado = arrecadado;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Campanha(CampanhaForm form){
        this.titulo = form.getTitulo();
        this.descricao = form.getDescricao();
        this.meta = form.getMeta();
        this.arrecadado = form.getArrecadado();
        this.dataInicio = form.getDataInicio();
        this.dataFim = form.getDataFim();
    }
}
