package com.backend.agendacrista.demo.controller.form;

import com.backend.agendacrista.demo.model.Igreja;
import com.backend.agendacrista.demo.repository.IgrejaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizaIgrejaForm {

    @NotNull @NotEmpty
    private String nome;
    @NotNull @NotEmpty
    private String descricao;
    @NotNull @NotEmpty
    private String imagem_url;
    @NotNull @NotEmpty
    private String rua;
    @NotNull
    private Integer numero;
    @NotNull @NotEmpty
    private String bairro;
    @NotNull @NotEmpty
    private String telefone;

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

    public Igreja converte(Long id, IgrejaRepository igrejaRepository) {
        Igreja igreja = igrejaRepository.getOne(id);
        igreja.setNome(nome);
        igreja.setDescricao(descricao);
        igreja.setImagem_url(imagem_url);
        igreja.setRua(rua);
        igreja.setNumero(numero);
        igreja.setBairro(bairro);
        igreja.setTelefone(telefone);
        return igreja;
    }
}
