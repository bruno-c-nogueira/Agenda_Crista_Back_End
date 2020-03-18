package com.backend.agendacrista.demo.controller.form;


import com.backend.agendacrista.demo.model.Noticia;
import com.backend.agendacrista.demo.repository.NoticiaRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizacaoNoticiaForm {
    @NotNull
    @NotEmpty
    private String titulo;
    @NotNull
    @NotEmpty
    private String imagemUrl;
    @NotNull
    @NotEmpty
    private String descricao;

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public Noticia atualizar(Long id, NoticiaRepository noticiaRepository) {
        Noticia noticia = noticiaRepository.getOne(id);
        noticia.setTitulo(this.titulo);
        noticia.setDescricao(this.descricao);
        noticia.setImagemUrl(this.imagemUrl);
        return noticia;
    }
}
