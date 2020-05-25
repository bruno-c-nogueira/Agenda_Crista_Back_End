package com.backend.agendacrista.demo.controller.dto;

import com.backend.agendacrista.demo.model.Endereco;
import com.backend.agendacrista.demo.model.Igreja;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;


public class IgrejaDto {

    private Long id;
    private String nome;
    private String descricao;
    private String imagem_url;
    private Endereco endereco;

    public IgrejaDto(Igreja igreja) {
        this.id = igreja.getId();
        this.nome = igreja.getNome();
        this.descricao = igreja.getDescricao();
        this.imagem_url = igreja.getImagem_url();
        this.endereco = igreja.getEndereco();
    }

    public static Page<IgrejaDto> converteIgrejaPageParaIgrejaDtoPage(Page<Igreja> igrejas) {

        return igrejas.map(IgrejaDto::new);
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

    public Endereco getEndereco() {
        return endereco;
    }
}
