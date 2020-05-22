package com.backend.agendacrista.demo.controller.form;

import com.backend.agendacrista.demo.model.DiaDaSemana;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class HorariosForm {
    @NotNull
    private DiaDaSemana diaDaSemana;
    @NotNull
    @NotEmpty
    private List<
            @Pattern(regexp="^([0-1][0-9]|[2][0-3]):([0-5][0-9])$",
                    message="Formato de hora inválido. Correto: 12:30")
                    String
            > horarios;

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
