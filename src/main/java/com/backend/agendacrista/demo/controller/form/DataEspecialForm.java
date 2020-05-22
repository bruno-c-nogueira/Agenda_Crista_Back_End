package com.backend.agendacrista.demo.controller.form;

import com.backend.agendacrista.demo.model.DataEspecial;
import com.backend.agendacrista.demo.repository.DataEspeciaisRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class DataEspecialForm {
    @NotEmpty @NotNull
    private LocalDateTime dataEspecial;
    @NotEmpty @NotNull
    private String descricaoData;
    @NotEmpty @NotNull
    private String tituloData;
    @NotEmpty @NotNull
    private String imagemData;


    public LocalDateTime getDataEspecial() {
        return dataEspecial;
    }

    public String getDescricaoData() {
        return descricaoData;
    }

    public String getTituloData() {
        return tituloData;
    }

    public String getImagemData() {
        return imagemData;
    }

    public DataEspecial converteDataEspecialFormParaDataEspecial(){
        return new DataEspecial(dataEspecial,descricaoData,imagemData);
    }
}
