package com.backend.agendacrista.demo.service;

import com.backend.agendacrista.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Async
    public void sendEmail(Usuario user, String assunto, String corpo) throws MailException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setSubject(assunto);
        mail.setText(corpo);
        javaMailSender.send(mail);
    }

}