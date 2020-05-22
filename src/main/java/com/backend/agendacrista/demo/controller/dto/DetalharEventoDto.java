package com.backend.agendacrista.demo.controller.dto;

import com.backend.agendacrista.demo.model.Evento;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DetalharEventoDto {
    private Long id;
    private String nome;
    private String descricao;
    private String imagem_url;
    private String nomeCelebrante;
    private IgrejaDto igreja;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private List<HorariosDto> horarios;


    public DetalharEventoDto(Evento evento) {
        this.id = evento.getId();
        this.nome = evento.getNome();
        this.descricao = evento.getDescricao();
        this.imagem_url = evento.getImagem_url();
        this.nomeCelebrante = evento.getNomeCelebrante();
        this.igreja = new IgrejaDto(evento.getIgreja());
        this.dataInicial = evento.getDataInicial();
        this.dataFinal = evento.getDataFinal();
        this.horarios = evento.getHorarios().stream().map(HorariosDto::new).collect(Collectors.toList());
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

    public IgrejaDto getIgreja() {
        return igreja;
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
}
