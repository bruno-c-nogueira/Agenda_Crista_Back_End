package com.backend.agendacrista.demo.controller.form;

import com.backend.agendacrista.demo.model.Cidade;
import com.backend.agendacrista.demo.model.Endereco;
import com.backend.agendacrista.demo.model.Igreja;
import com.backend.agendacrista.demo.repository.CidadeRepository;
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
    @NotNull
    private EnderecoForm endereco;
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

    public Igreja converte(Long id, IgrejaRepository igrejaRepository, CidadeRepository cidadeRepository) {
        Igreja igreja = igrejaRepository.getOne(id);
        igreja.setNome(nome);
        igreja.setDescricao(descricao);
        igreja.setImagem_url(imagem_url);
        igreja.setTelefone(telefone);
        Endereco endereco = igreja.getEndereco();
        endereco.setRua(this.endereco.getRua());
        endereco.setNumero(this.endereco.getNumero());
        endereco.setBairro(this.endereco.getBairro());
        endereco.setComplemento(this.endereco.getComplemento());
        Cidade cidade = cidadeRepository.getOne(this.endereco.getCidade_id());
        endereco.setCidade(cidade);
        return igreja;
    }
}
