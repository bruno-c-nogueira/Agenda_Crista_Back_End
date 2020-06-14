package com.backend.agendacrista.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilderService {

    @Autowired
    TemplateEngine templateEngine;

    public String build(String link, String nome, String template) {
        Context context = new Context();
        context.setVariable("nome", nome);
        context.setVariable("link", link);
        return templateEngine.process(template, context);
    }
}
