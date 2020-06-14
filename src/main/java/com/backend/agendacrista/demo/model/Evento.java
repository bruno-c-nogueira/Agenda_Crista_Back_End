package com.backend.agendacrista.demo.model;

import com.backend.agendacrista.demo.controller.form.EventoForm;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Evento extends AbstractEntity {
    private String titulo;
    private String descricao;
    @ManyToOne
    private Igreja igreja;
    private String nomeCelebrante;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Horarios> horarios;
    @ManyToOne
    private Usuario usuario;
    private LocalDateTime criadoEm = LocalDateTime.now();


    public Evento(EventoForm form, Igreja igreja, Usuario usuario, List<Horarios> horarios) {
        this.titulo = form.getTitulo();
        this.descricao = form.getDescricao();
        this.igreja = igreja;
        this.nomeCelebrante = form.getNomeCelebrante();
        this.dataInicial = form.getDataInicial();
        this.dataFinal = form.getDataFinal();
        this.horarios = horarios;
        this.usuario = usuario;
    }

    public Evento() {

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String nome) {
        this.titulo = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Igreja getIgreja() {
        return igreja;
    }

    public void setIgreja(Igreja igreja) {
        this.igreja = igreja;
    }

    public String getNomeCelebrante() {
        return nomeCelebrante;
    }

    public void setNomeCelebrante(String nomeCelebrante) {
        this.nomeCelebrante = nomeCelebrante;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<Horarios> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horarios> horarios) {
        this.horarios = horarios;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}
