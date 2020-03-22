package com.backend.agendacrista.demo.controller.dto;

import com.backend.agendacrista.demo.model.Noticia;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NoticiaDto {
    private Long id;
    private String titulo;
    private String descricao;
    private String imagemUrl;
    private LocalDateTime dataCriacao;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public NoticiaDto(Noticia noticia) {
        this.id = noticia.getId();
        this.titulo = noticia.getTitulo();
        this.descricao = noticia.getDescricao();
        this.imagemUrl = noticia.getImagemUrl();
        this.dataCriacao = noticia.getDataCriacao();
    }

    public static Page<NoticiaDto> converter(Page<Noticia> noticias){
        return  noticias.map(NoticiaDto::new);
    }
}
