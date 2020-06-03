package com.backend.agendacrista.demo.controller.form;

import com.backend.agendacrista.demo.model.DataEspecial;
import com.backend.agendacrista.demo.repository.DataEspeciaisRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class DataEspecialForm {
    private LocalDateTime dataEspecial;
    @NotEmpty @NotNull
    private String descricaoData;
    @NotEmpty @NotNull
    private String tituloData;
    @NotEmpty @NotNull
    private String imagemData;

    public DataEspecialForm(@NotEmpty @NotNull LocalDateTime dataEspecial, @NotEmpty @NotNull String descricaoData, @NotEmpty @NotNull String tituloData, @NotEmpty @NotNull String imagemData) {
        this.dataEspecial = dataEspecial;
        this.descricaoData = descricaoData;
        this.tituloData = tituloData;
        this.imagemData = imagemData;
    }

    public DataEspecialForm() {
    }

    public LocalDateTime getDataEspecial() {
        return dataEspecial;
    }

    public void setDataEspecial(LocalDateTime dataEspecial) {
        this.dataEspecial = dataEspecial;
    }

    public String getDescricaoData() {
        return descricaoData;
    }

    public void setDescricaoData(String descricaoData) {
        this.descricaoData = descricaoData;
    }

    public String getTituloData() {
        return tituloData;
    }

    public void setTituloData(String tituloData) {
        this.tituloData = tituloData;
    }

    public String getImagemData() {
        return imagemData;
    }

    public void setImagemData(String imagemData) {
        this.imagemData = imagemData;
    }

    public DataEspecial converteDataEspecialFormParaDataEspecial(){
        return new DataEspecial(dataEspecial,descricaoData,imagemData);
    }
}
