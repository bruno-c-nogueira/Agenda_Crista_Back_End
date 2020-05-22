package com.backend.agendacrista.demo.controller.dto;

import com.backend.agendacrista.demo.model.DiaDaSemana;
import com.backend.agendacrista.demo.model.Horarios;

import java.util.List;

public class HorariosDto {
    private DiaDaSemana diaDaSemana;
    private List<String> horarios;

    public HorariosDto() {
    }

    public HorariosDto(Horarios horarios) {
        this.diaDaSemana = horarios.getDiaDaSemana();
        this.horarios = horarios.getHorarios();
    }

    public DiaDaSemana getDiaDaSemana() {
        return diaDaSemana;
    }

    public List<String> getHorarios() {
        return horarios;
    }
}
