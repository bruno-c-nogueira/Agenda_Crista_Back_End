package com.backend.agendacrista.demo.controller.dto;

import com.backend.agendacrista.demo.model.Cidade;

import java.util.List;
import java.util.stream.Collectors;

public class CidadeDto {
    private Integer id;
    private String nome;

    public CidadeDto(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
    }

    public static List<CidadeDto> converte(List<Cidade> cidades) {
        return cidades.stream().map(CidadeDto::new).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
