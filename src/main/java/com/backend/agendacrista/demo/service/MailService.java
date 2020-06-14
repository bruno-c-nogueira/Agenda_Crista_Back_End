package com.backend.agendacrista.demo.service;

import com.backend.agendacrista.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MailContentBuilderService mcbService;

    @Value("${spring.mail.username}")
    String fromEmail;

    @Async
    public void sendEmail(Usuario user, String assunto, String corpo) throws MailException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setSubject(assunto);
        mail.setText(corpo);
        javaMailSender.send(mail);
    }
    @Async
    public void sendMailTemplate(Usuario user, String assunto, String link,String template) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(fromEmail, "Agenda Cristã");
            messageHelper.setTo(user.getEmail());
            messageHelper.setSubject(assunto);
            String content = mcbService.build(link, user.getNome(), template);
            messageHelper.setText(content, true);
        };
        javaMailSender.send(messagePreparator);
    }


}