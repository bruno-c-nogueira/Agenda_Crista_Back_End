package com.backend.agendacrista.demo.controller.form;

import com.backend.agendacrista.demo.model.Evento;
import com.backend.agendacrista.demo.repository.EventoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AtualizaEventoForm {
    @NotNull @NotEmpty
    private String nome;
    @NotNull @NotEmpty
    private String descricao;
    @NotNull @NotEmpty
    private String imagem_url;
    @NotNull @NotEmpty
    private String nomeCelebrante;
    @NotNull
    private LocalDateTime data;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem_url() {
        return imagem_url;
    }

    public void setImagem_url(String imagem_url) {
        this.imagem_url = imagem_url;
    }


    public String getNomeCelebrante() {
        return nomeCelebrante;
    }

    public void setNomeCelebrante(String nomeCelebrante) {
        this.nomeCelebrante = nomeCelebrante;
    }


    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Evento converte(Long id, EventoRepository eventoRepository) {
        Evento evento = eventoRepository.getOne(id);
        evento.setNome(this.nome);
        evento.setDescricao(this.descricao);
        evento.setImagem_url(this.imagem_url);
        evento.setNomeCelebrante(this.nomeCelebrante);
        evento.setData(this.data);
        return evento;
    }
}
