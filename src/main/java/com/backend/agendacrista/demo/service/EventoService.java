package com.backend.agendacrista.demo.service;

import com.backend.agendacrista.demo.controller.dto.DetalharIgrejaDto;
import com.backend.agendacrista.demo.controller.form.EventoForm;
import com.backend.agendacrista.demo.error.ResourceNotFoundException;
import com.backend.agendacrista.demo.model.*;
import com.backend.agendacrista.demo.repository.EventoRepository;
import com.backend.agendacrista.demo.repository.HorariosRepository;
import com.backend.agendacrista.demo.repository.IgrejaRepository;
import com.backend.agendacrista.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventoService {
    @Autowired
    EventoRepository eventoRepository;
    @Autowired
    IgrejaRepository igrejaRepository;
    @Autowired
    HorariosRepository horariosRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
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

    public void enviaNotificacaoEvento(Evento evento) {
        Igreja igreja = igrejaRepository.getOne(evento.getIgreja().getId());
        String title = igreja.getNome() + ": " + evento.getTitulo();
        String body = evento.getDescricao();
        List<String> registrationIds = new ArrayList<>();
        List<Usuario> byIgrejasFavoritasContaining = usuarioRepository.findAllByIgrejasFavoritasContaining(igreja);
        byIgrejasFavoritasContaining.forEach(usuario -> registrationIds.add(usuario.getTokenFcm()));
        PushFcmAbstract pushFcmAbstract = new PushFcmRegistrationIds(registrationIds, new PushFcmNotification(title, body));
        pushFcmAbstract.setData(new DetalharIgrejaDto(igreja));
        pnfcmService.sendNotification(pushFcmAbstract);

    }

    public void deletarEventoPorIdIgreja(Long id) {
        eventoRepository.deleteByIgrejaId(id);
    }
}
