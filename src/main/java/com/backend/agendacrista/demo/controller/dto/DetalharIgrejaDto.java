package com.backend.agendacrista.demo.controller.dto;

import com.backend.agendacrista.demo.model.Cidade;
import com.backend.agendacrista.demo.model.Igreja;

public class DetalharIgrejaDto {
    private Long id;
    private String nome;
    private String descricao;
    private String imagem_url;
    private CidadeDto cidade;


    public DetalharIgrejaDto(Igreja igreja) {
        this.id = igreja.getId();
        this.nome = igreja.getNome();
        this.descricao = igreja.getDescricao();
        this.imagem_url = igreja.getImagem_url();
        this.cidade = new CidadeDto(igreja.getCidade());
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

    public CidadeDto getCidade() {
        return cidade;
    }
}
