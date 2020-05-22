package com.backend.agendacrista.demo.model;

import com.backend.agendacrista.demo.controller.form.HorariosForm;

import javax.persistence.*;
import java.util.List;

@Entity
public class Horarios extends AbstractEntity {
    @Enumerated(value = EnumType.STRING)
    private DiaDaSemana diaDaSemana;
    @ElementCollection(targetClass = String.class)
    @CollectionTable
    private List<String> horarios;

    public Horarios() {
    }

    public Horarios(HorariosForm horariosForm) {
        this.diaDaSemana = horariosForm.getDiaDaSemana();
        this.horarios = horariosForm.getHorarios();
    }

    public DiaDaSemana getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public List<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<String> horarios) {
        this.horarios = horarios;
    }
}
