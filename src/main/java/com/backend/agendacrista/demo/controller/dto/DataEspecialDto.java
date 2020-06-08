package com.backend.agendacrista.demo.controller.dto;

import com.backend.agendacrista.demo.model.DataEspecial;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataEspecialDto {
    private Long id;
    private LocalDateTime dataEspecial;
    private String descricao;
    private String imagem;
    private String titulo;

    public DataEspecialDto(DataEspecial dataEspecial) {
        this.id = dataEspecial.getId();
        this.dataEspecial = dataEspecial.getDataEspecial();
        this.descricao = dataEspecial.getDescricaoData();
        this.imagem = dataEspecial.getImagemData();
        this.titulo = dataEspecial.getTituloData();
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public static List<DataEspecialDto> converteDataEspecialListParaDataEspecialDtoList(List<DataEspecial> dataEspecia){
        List<DataEspecialDto> list = new ArrayList<>();
        dataEspecia.forEach(dataEspecialfor -> {
            list.add(new DataEspecialDto(dataEspecialfor));
        });
        return list;
    }
}
