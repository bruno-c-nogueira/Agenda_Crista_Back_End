package com.backend.agendacrista.demo.controller.dto;

import com.backend.agendacrista.demo.model.Endereco;

public class EnderecoDto {
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private CidadeDto cidade;
    private EstadoDto estado;

    public EnderecoDto(Endereco endereco) {
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.cidade = new CidadeDto(endereco.getCidade());
        this.estado = new EstadoDto(endereco.getCidade().getUf());
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public CidadeDto getCidade() {
        return cidade;
    }

    public EstadoDto getEstado() {
        return estado;
    }
}
