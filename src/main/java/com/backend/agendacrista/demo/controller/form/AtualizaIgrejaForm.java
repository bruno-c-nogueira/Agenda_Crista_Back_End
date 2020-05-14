package com.backend.agendacrista.demo.controller.form;

import com.backend.agendacrista.demo.model.Endereco;
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
    private Endereco endereco;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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
        igreja.setEndereco(endereco);
        igreja.setTelefone(telefone);
        return igreja;
    }
}
