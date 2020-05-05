package com.backend.agendacrista.demo.controller.form;


import com.backend.agendacrista.demo.model.Noticia;
import com.backend.agendacrista.demo.repository.NoticiaRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AtualizacaoNoticiaForm {
    @NotEmpty @NotNull
    private String titulo;
    @NotEmpty @NotNull
    private String imagemUrl;
    @NotEmpty @NotNull
    private String descricao;
    @NotEmpty @NotNull
    private LocalDateTime dataCriacao;

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


    public Noticia atualizar(Long id, NoticiaRepository noticiaRepository) {
        Noticia noticia = noticiaRepository.getOne(id);
        noticia.setTitulo(this.titulo);
        noticia.setDescricao(this.descricao);
        noticia.setImagemUrl(this.imagemUrl);
        noticia.setDataCriacao(this.dataCriacao);
        return noticia;
    }
}
