package com.backend.agendacrista.demo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class DataEspecial extends AbstractEntity {

    private LocalDateTime dataEspecial;
    @Column(columnDefinition="TEXT")
    private String descricaoData;
    private String tituloData;
    private String imagemData;

    public DataEspecial(LocalDateTime dataEspecial, String descricaoData, String imagemData) {
        super();
        this.dataEspecial = dataEspecial;
        this.descricaoData = descricaoData;
        this.imagemData = imagemData;
    }

    public DataEspecial() {
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
