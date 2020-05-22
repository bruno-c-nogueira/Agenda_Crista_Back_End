package com.backend.agendacrista.demo.service;

import com.backend.agendacrista.demo.controller.form.EventoForm;
import com.backend.agendacrista.demo.error.ResourceNotFoundException;
import com.backend.agendacrista.demo.model.Evento;
import com.backend.agendacrista.demo.repository.EventoRepository;
import com.backend.agendacrista.demo.repository.HorariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService {
    @Autowired
    EventoRepository eventoRepository;
    @Autowired
    HorariosRepository horariosRepository;

    public void verificaSeIdEventoExiste(Long id) {
        if (eventoRepository.findById(id).isEmpty())
            throw new ResourceNotFoundException("Id Evento inválido");
    }
    
    public Evento atualizaEvento(Long idEvento, EventoForm eventoForm){
        Evento evento = eventoRepository.getOne(idEvento);
        evento.getHorarios().forEach(horario -> horariosRepository.delete(horario));
        return eventoRepository.save(eventoForm.converteEventoFormParaEventoESetId(idEvento));
    }
}
