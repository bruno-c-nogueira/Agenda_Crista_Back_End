package com.backend.agendacrista.demo.controller.dto;

import com.backend.agendacrista.demo.model.Estado;

import java.util.List;
import java.util.stream.Collectors;

public class EstadoDto {

    private Long id;
    private String nome;
    private String uf;
    private String imagem_estado;

    public EstadoDto(Estado estado) {
        this.id = estado.getId();
        this.nome = estado.getNome();
        this.uf = estado.getUf();
        this.imagem_estado = estado.getImagem_estado();
    }

    public static List<EstadoDto> converte(List<Estado> estados) {
        return estados.stream().map(EstadoDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUf() {
        return uf;
    }

    public String getImagem_estado() {
        return imagem_estado;
    }
}
