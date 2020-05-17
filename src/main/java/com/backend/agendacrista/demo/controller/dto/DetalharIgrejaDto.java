package com.backend.agendacrista.demo.controller.dto;

import com.backend.agendacrista.demo.model.Igreja;
import com.backend.agendacrista.demo.model.StatusIgreja;

import java.util.List;
import java.util.stream.Collectors;

public class DetalharIgrejaDto {
    private Long id;
    private String nome;
    private String descricao;
    private String imagem_url;
    private StatusIgreja statusIgreja;
    private EnderecoDto endereco;


    public DetalharIgrejaDto(Igreja igreja) {
        this.id = igreja.getId();
        this.nome = igreja.getNome();
        this.descricao = igreja.getDescricao();
        this.imagem_url = igreja.getImagem_url();
        this.statusIgreja = igreja.getStatusIgreja();
        this.endereco = new EnderecoDto(igreja.getEndereco());

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

    public EnderecoDto getEndereco() {
        return endereco;
    }

    public StatusIgreja getStatusIgreja() {
        return statusIgreja;
    }

    public static List<DetalharIgrejaDto> converte(List<Igreja> igrejas) {
        return igrejas.stream().map(DetalharIgrejaDto::new).collect(Collectors.toList());
    }
}
