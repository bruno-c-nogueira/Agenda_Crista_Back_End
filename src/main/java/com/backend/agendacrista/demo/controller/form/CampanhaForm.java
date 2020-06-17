package com.backend.agendacrista.demo.controller.form;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CampanhaForm {
    @Valid
    @NotEmpty@NotNull(message = "Campo titulo é obrigatorio")
    private String titulo;
    @Valid
    @NotEmpty@NotNull(message = "Campo descricao é obrigatorio")
    private String descricao;
    @Valid
    @NotEmpty@NotNull(message = "Campo meta é obrigatorio")
    private Double meta;
    @Valid
    @NotEmpty@NotNull(message = "Campo arrecadado é obrigatorio")
    private Double arrecadado;
    @Valid
    @NotEmpty@NotNull(message = "Campo dataInicio é obrigatorio")
    private LocalDate dataInicio;
    @Valid
    @NotEmpty@NotNull(message = "Campo dataFim é obrigatorio")
    private LocalDate dataFim;

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
}
