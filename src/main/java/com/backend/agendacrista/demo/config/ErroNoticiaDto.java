package com.backend.agendacrista.demo.config;

public class ErroNoticiaDto {

    private String campo;
    private String erro;

    public ErroNoticiaDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
