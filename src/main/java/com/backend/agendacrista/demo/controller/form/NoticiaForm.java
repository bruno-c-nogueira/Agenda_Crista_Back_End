package com.backend.agendacrista.demo.controller.form;

import com.backend.agendacrista.demo.model.Noticia;
import com.backend.agendacrista.demo.repository.NoticiaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NoticiaForm {
    @NotEmpty@NotNull
    private String titulo;
    @NotEmpty@NotNull
    private String descricao;
    @NotEmpty@NotNull
    private String imagemUrl;

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public Noticia converter(){
        return new Noticia(titulo,descricao,imagemUrl);
    }
}
