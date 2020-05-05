package com.backend.agendacrista.demo.controller.form;

import com.backend.agendacrista.demo.model.DataEspecial;
import com.backend.agendacrista.demo.repository.DataEspeciaisRepository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class DataEspecialForm {
    @NotNull
    private LocalDateTime dataEspecial;
    @NotNull
    private String descricaoData;
    @NotNull
    private String tituloData;
    @NotNull
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

    public DataEspecial converter(){
        return new DataEspecial(dataEspecial,descricaoData,imagemData);
    }
}
