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

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }


    public NoticiaDto(Noticia noticia) {
        this.id = noticia.getId();
        this.titulo = noticia.getTitulo();
        this.descricao = noticia.getDescricao();
        this.imagemUrl = noticia.getImagemUrl();
        this.dataCriacao = noticia.getDataCriacao();
    }

    public static Page<NoticiaDto> converteNoticiaPageParaNoticiaDtoPage(Page<Noticia> noticias){
        return  noticias.map(NoticiaDto::new);
    }
}
