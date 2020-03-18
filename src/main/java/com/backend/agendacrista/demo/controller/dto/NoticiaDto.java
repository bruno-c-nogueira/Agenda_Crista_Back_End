package com.backend.agendacrista.demo.controller.dto;

import com.backend.agendacrista.demo.model.Noticia;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NoticiaDto {
    private Long id;
    private String titulo;
    private String descricao;
    private String imagemUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    }

    public static List<NoticiaDto> converter(List<Noticia> noticias){
        List<NoticiaDto> dtoList = new ArrayList<>();
        noticias.forEach(noticia -> {
            dtoList.add(new NoticiaDto(noticia));
        });
        return  dtoList;
    }
}
