package com.backend.agendacrista.demo.model;

import com.backend.agendacrista.demo.controller.form.IgrejaForm;

import javax.persistence.*;
import java.util.List;

@Entity
public class Igreja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;

    private String imagem_url;

    @ManyToOne
    private Cidade cidade;
    private String rua;
    private Integer numero;
    private String bairro;
    private String telefone;

    @ManyToOne
    private Usuario usuario;

    @OneToMany
    private List<Evento> eventos;

    public Igreja(IgrejaForm igrejaForm, Cidade cidade, Usuario usuario) {
        this.nome = igrejaForm.getNome();
        this.descricao = igrejaForm.getDescricao();
        this.imagem_url = igrejaForm.getImagem_url();
        this.cidade = cidade;
        this.rua = igrejaForm.getRua();
        this.numero = igrejaForm.getNumero();
        this.bairro = igrejaForm.getBairro();
        this.telefone = igrejaForm.getTelefone();
        this.usuario = usuario;
    }

    public Igreja() {

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

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}
