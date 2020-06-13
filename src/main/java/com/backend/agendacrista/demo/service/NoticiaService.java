package com.backend.agendacrista.demo.service;

import com.backend.agendacrista.demo.controller.dto.NoticiaDto;
import com.backend.agendacrista.demo.error.ResourceNotFoundException;
import com.backend.agendacrista.demo.model.*;
import com.backend.agendacrista.demo.repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoticiaService {

    @Autowired
    PushNotificationFCMService pnfcmService;

    @Autowired
    NoticiaRepository noticiaRepository;

    @Value("${agenda.fcm.topics.global}")
    private String topicGlobal;

    public void enviaNotificacao(Noticia noticia) {
        String title = noticia.getTitulo();
        String body = noticia.getDescricao();
        PushFcmAbstract pushFcmAbstract = new PushFcmTo(topicGlobal, new PushFcmNotification(title, body));
        PushFcmData pushFcmData = new PushFcmData("FLUTTER_NOTIFICATION_CLICK", "noticia", noticia.getId());
        pushFcmAbstract.setData(pushFcmData);
        pnfcmService.sendNotification(pushFcmAbstract);
    }

    public void verificaSeIdNoticiaExiste(Long id) {
        if (noticiaRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Id Noticia inválido");
        }
    }
}
