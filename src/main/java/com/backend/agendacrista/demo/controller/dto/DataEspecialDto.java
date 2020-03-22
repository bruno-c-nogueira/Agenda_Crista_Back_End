package com.backend.agendacrista.demo.controller.dto;

import com.backend.agendacrista.demo.model.DataEspecial;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataEspecialDto {
    private Long id;
    private LocalDateTime dataEspecial;
    private String descricaoData;
    private String imagemData;

    public DataEspecialDto(DataEspecial dataEspecial) {
        this.id = dataEspecial.getId();
        this.dataEspecial = dataEspecial.getDataEspecial();
        this.descricaoData = dataEspecial.getDescricaoData();
        this.imagemData = dataEspecial.getImagemData();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImagemData() {
        return imagemData;
    }

    public void setImagemData(String imagemData) {
        this.imagemData = imagemData;
    }

    public static List<DataEspecialDto> converter(List<DataEspecial> dataEspecia){
        List<DataEspecialDto> list = new ArrayList<>();
        dataEspecia.forEach(dataEspecialfor -> {
            list.add(new DataEspecialDto(dataEspecialfor));
        });
        return list;
    }
}
