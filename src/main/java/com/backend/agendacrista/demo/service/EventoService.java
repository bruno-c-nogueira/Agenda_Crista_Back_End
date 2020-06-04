package com.backend.agendacrista.demo.service;

import com.backend.agendacrista.demo.controller.form.EventoForm;
import com.backend.agendacrista.demo.error.ResourceNotFoundException;
import com.backend.agendacrista.demo.model.Evento;
import com.backend.agendacrista.demo.model.Igreja;
import com.backend.agendacrista.demo.model.PushFCM;
import com.backend.agendacrista.demo.model.PushFCMNotification;
import com.backend.agendacrista.demo.repository.EventoRepository;
import com.backend.agendacrista.demo.repository.HorariosRepository;
import com.backend.agendacrista.demo.repository.IgrejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService {
    @Autowired
    EventoRepository eventoRepository;
    @Autowired
    IgrejaRepository igrejaRepository;
    @Autowired
    HorariosRepository horariosRepository;
    @Autowired
    PushNotificationFCMService pnfcmService;

    public void verificaSeIdEventoExiste(Long id) {
        if (eventoRepository.findById(id).isEmpty())
            throw new ResourceNotFoundException("Id Evento inválido");
    }

    public Evento atualizaEvento(Long idEvento, EventoForm eventoForm) {
        Evento evento = eventoRepository.getOne(idEvento);
        evento.getHorarios().forEach(horario -> horariosRepository.delete(horario));
        return eventoRepository.save(eventoForm.converteEventoFormParaEventoESetId(idEvento));
    }

    public void enviaNotificacaoEvento(Evento evento, String topico) {
        Igreja igreja = igrejaRepository.getOne(evento.getIgreja().getId());
        String title = igreja.getNome() + ": " + evento.getTitulo();
        String body = evento.getDescricao();
        PushFCM pushFCM = new PushFCM(topico, new PushFCMNotification(title, body));
        pnfcmService.sendNotification(pushFCM);

    }
}
