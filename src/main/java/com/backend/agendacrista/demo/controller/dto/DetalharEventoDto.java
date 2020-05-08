package com.backend.agendacrista.demo.controller.dto;

import com.backend.agendacrista.demo.model.Cidade;
import com.backend.agendacrista.demo.model.Evento;
import com.backend.agendacrista.demo.model.Igreja;

import java.time.LocalDateTime;

public class DetalharEventoDto {
    private Long id;
    private String nome;
    private String descricao;
    private String imagem_url;
    private String nomeCelebrante;
    private LocalDateTime data;
    private Igreja igreja;
    private Cidade cidade;


    public DetalharEventoDto(Evento evento) {
        this.id = evento.getId();
        this.nome = evento.getNome();
        this.descricao = evento.getDescricao();
        this.imagem_url = evento.getImagem_url();
        this.nomeCelebrante = evento.getNomeCelebrante();
        this.data = evento.getData();
        this.igreja = evento.getIgreja();
        this.cidade = evento.getCidade();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getImagem_url() {
        return imagem_url;
    }

    public String getNomeCelebrante() {
        return nomeCelebrante;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Igreja getIgreja() {
        return igreja;
    }

    public Cidade getCidade() {
        return cidade;
    }
}
