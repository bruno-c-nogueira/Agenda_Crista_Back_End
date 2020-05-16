package com.backend.agendacrista.demo.model;

import com.backend.agendacrista.demo.controller.form.EnderecoForm;

import javax.persistence.*;

@Entity
public class Endereco {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    @ManyToOne
    private Cidade cidade;

    public Endereco() {

    }

    public Endereco(EnderecoForm enderecoForm, Cidade cidade) {
        this.rua = enderecoForm.getRua();
        this.numero = enderecoForm.getNumero();
        this.complemento = enderecoForm.getComplemento();
        this.bairro = enderecoForm.getBairro();
        this.cidade = cidade;
    }

    public int getId() {
        return id;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
