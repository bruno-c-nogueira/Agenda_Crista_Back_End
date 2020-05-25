package com.backend.agendacrista.demo.controller.dto;

import com.backend.agendacrista.demo.model.Evento;
import org.springframework.data.domain.Page;

public class EventoDto {
    private Long id;
    private String titulo;
    private String descricao;
    private String nomeCelebrante;


    public EventoDto(Evento evento) {
        this.id = evento.getId();
        this.titulo = evento.getTitulo();
        this.descricao = evento.getDescricao();
        this.nomeCelebrante = evento.getNomeCelebrante();
    }


    public static Page<EventoDto> converteEventoPageParaEventoDtoPage(Page<Evento> eventos) {
        return eventos.map(EventoDto::new);
    }

    public Long getId() {
        return id;
    }


    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNomeCelebrante() {
        return nomeCelebrante;
    }


}
