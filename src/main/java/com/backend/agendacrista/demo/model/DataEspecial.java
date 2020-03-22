package com.backend.agendacrista.demo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
public class DataEspecial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataEspecial;
    private String descricaoData;
    private String imagemData;

    public DataEspecial(LocalDateTime dataEspecial,String descricaoData,String imagemData) {
        super();
        this.dataEspecial = dataEspecial;
        this.descricaoData = descricaoData;
        this.imagemData = imagemData;
    }

    public DataEspecial() {
    }

    public String getImagemData() {
        return imagemData;
    }

    public void setImagemData(String imagemData) {
        this.imagemData = imagemData;
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
}
