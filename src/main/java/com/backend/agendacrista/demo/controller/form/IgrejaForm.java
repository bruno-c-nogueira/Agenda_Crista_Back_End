package com.backend.agendacrista.demo.controller.form;

import com.backend.agendacrista.demo.model.Cidade;
import com.backend.agendacrista.demo.model.Endereco;
import com.backend.agendacrista.demo.model.Igreja;
import com.backend.agendacrista.demo.model.Usuario;
import com.backend.agendacrista.demo.service.UsusarioService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class IgrejaForm {

    @NotNull @NotEmpty
    private String nome;
    @NotNull @NotEmpty
    private String descricao;
    @NotNull @NotEmpty
    private String imagem_url;
    @NotNull
    private EnderecoForm endereco;
    @NotNull @NotEmpty
    private String telefone;
    @NotNull @NotEmpty
    private String doc_imagem_url;

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

    public EnderecoForm getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoForm endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDoc_imagem_url() {
        return doc_imagem_url;
    }

    public void setDoc_imagem_url(String doc_imagem_url) {
        this.doc_imagem_url = doc_imagem_url;
    }

    public Igreja converteIgrejaFormParaIgreja() {
        Endereco endereco = new Endereco(this.getEndereco(), new Cidade(this.getEndereco().getCidade_id()));
        Igreja igreja = new Igreja(this, new Usuario(UsusarioService.getIdUsuarioLogado()), endereco);
        return igreja;
    }
}
