package com.backend.agendacrista.demo.controller.dto;

import com.backend.agendacrista.demo.model.Evento;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DetalharEventoDto {
    private Long id;
    private String titulo;
    private String descricao;
    private String nomeCelebrante;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private List<HorariosDto> horarios;


    public DetalharEventoDto(Evento evento) {
        this.id = evento.getId();
        this.titulo = evento.getTitulo();
        this.descricao = evento.getDescricao();
        this.nomeCelebrante = evento.getNomeCelebrante();
        this.dataInicial = evento.getDataInicial();
        this.dataFinal = evento.getDataFinal();
        this.horarios = evento.getHorarios().stream().map(HorariosDto::new).collect(Collectors.toList());
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

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public List<HorariosDto> getHorarios() {
        return horarios;
    }

    public static List<DetalharEventoDto> converteEventoListParaDetalharEventoDto(List<Evento> eventoList) {
        return eventoList.stream().map(DetalharEventoDto::new).collect(Collectors.toList());
    }
}
