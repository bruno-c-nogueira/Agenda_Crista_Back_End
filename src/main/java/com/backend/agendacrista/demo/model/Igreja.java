package com.backend.agendacrista.demo.model;

import com.backend.agendacrista.demo.controller.form.IgrejaForm;

import javax.persistence.*;

@Entity
public class Igreja extends AbstractEntity {
    private String nome;
    private String descricao;
    private String imagem_url;
    private String telefone;
    @ManyToOne
    private Usuario usuario;
    @OneToOne
    private Endereco endereco;
    @Enumerated(value = EnumType.STRING)
    private StatusIgreja statusIgreja = StatusIgreja.EM_ANALISE;
    private String doc_imagem_url;


    public Igreja() {

    }

    public Igreja(IgrejaForm form, Usuario usuario, Endereco endereco) {
        this.endereco = endereco;
        this.nome = form.getNome();
        this.descricao = form.getDescricao();
        this.usuario = usuario;
        this.telefone = form.getTelefone();
        this.imagem_url = form.getImagem_url();
        this.doc_imagem_url = form.getDoc_imagem_url();
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Igreja(Long igreja_id) {
        this.setId(igreja_id);
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

    public StatusIgreja getStatusIgreja() {
        return statusIgreja;
    }

    public void setStatusIgreja(StatusIgreja statusIgreja) {
        this.statusIgreja = statusIgreja;
    }

    public String getDoc_imagem_url() {
        return doc_imagem_url;
    }

    public void setDoc_imagem_url(String doc_imagem_url) {
        this.doc_imagem_url = doc_imagem_url;
    }
}
