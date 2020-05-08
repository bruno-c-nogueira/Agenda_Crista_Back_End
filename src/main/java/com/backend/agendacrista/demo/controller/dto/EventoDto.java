package com.backend.agendacrista.demo.controller.dto;

import com.backend.agendacrista.demo.model.Evento;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class EventoDto {
    private Long id;
    private String nome;
    private String descricao;
    private String imagem_url;
    private String nomeCelebrante;
    private LocalDateTime data;


    public EventoDto(Evento evento) {
        this.id = evento.getId();
        this.nome = evento.getNome();
        this.descricao = evento.getDescricao();
        this.imagem_url = evento.getImagem_url();
        this.nomeCelebrante = evento.getNomeCelebrante();
        this.data = evento.getData();
    }


    public static Page<EventoDto> converte(Page<Evento> eventos) {
        return eventos.map(EventoDto::new);
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

    public void setNomeCelebrante(String nomeCelebrante) {
        this.nomeCelebrante = nomeCelebrante;
    }

    public LocalDateTime getData() {
        return data;
    }

}
