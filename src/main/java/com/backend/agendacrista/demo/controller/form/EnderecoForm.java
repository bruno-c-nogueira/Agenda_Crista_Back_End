package com.backend.agendacrista.demo.controller.form;

import com.backend.agendacrista.demo.model.Endereco;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EnderecoForm {

    @NotEmpty @NotNull
    private String rua;
    @NotEmpty @NotNull
    private String numero;
    @NotEmpty @NotNull
    private String complemento;
    @NotEmpty @NotNull
    private String bairro;
    @NotNull
    private Integer cidade_id;

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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getCidade_id() {
        return cidade_id;
    }

    public void setCidade_id(Integer cidade_id) {
        this.cidade_id = cidade_id;
    }

    public Endereco getEndereco() {
        return new Endereco();
    }
}
