package com.backend.agendacrista.demo.controller.dto;

import com.backend.agendacrista.demo.model.Igreja;

public class DetalharIgrejaDto {
    private Long id;
    private String nome;
    private String descricao;
    private String imagem_url;
    private EnderecoDto endereco;


    public DetalharIgrejaDto(Igreja igreja) {
        this.id = igreja.getId();
        this.nome = igreja.getNome();
        this.descricao = igreja.getDescricao();
        this.imagem_url = igreja.getImagem_url();
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
}
