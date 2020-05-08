package com.backend.agendacrista.demo.model;

import com.backend.agendacrista.demo.controller.form.EventoForm;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;

    private String imagem_url;

    @ManyToOne
    private Igreja igreja;

    private String nomeCelebrante;

    @ManyToOne
    private Cidade cidade;

    private LocalDateTime data;

    @ManyToOne
    private Usuario usuario;

    @ManyToMany
    private List<Usuario> adms;

    public Evento(EventoForm form, Cidade cidade, Igreja igreja, Usuario usuario) {
        this.nome = form.getNome();
        this.descricao = form.getDescricao();
        this.imagem_url = form.getImagem_url();
        this.igreja = igreja;
        this.nomeCelebrante = form.getNomeCelebrante();
        this.cidade = cidade;
        this.data = form.getData();
        this.usuario = usuario;

    }

    public Evento() {

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Igreja getIgreja() {
        return igreja;
    }

    public void setIgreja(Igreja igreja) {
        this.igreja = igreja;
    }

    public String getNomeCelebrante() {
        return nomeCelebrante;
    }

    public void setNomeCelebrante(String nomeCelebrante) {
        this.nomeCelebrante = nomeCelebrante;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public List<Usuario> getAdms() {
        return adms;
    }

    public void setAdms(List<Usuario> adms) {
        this.adms = adms;
    }
}
