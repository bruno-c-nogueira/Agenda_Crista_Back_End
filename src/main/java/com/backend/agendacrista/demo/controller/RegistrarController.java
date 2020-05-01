package com.backend.agendacrista.demo.controller;

import com.backend.agendacrista.demo.controller.dto.NoticiaDto;
import com.backend.agendacrista.demo.controller.form.AtualizacaoNoticiaForm;
import com.backend.agendacrista.demo.controller.form.LoginForm;
import com.backend.agendacrista.demo.controller.form.RegisterForm;
import com.backend.agendacrista.demo.model.Noticia;
import com.backend.agendacrista.demo.model.Usuario;
import com.backend.agendacrista.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrarController {

    @Autowired
    UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<NoticiaDto> registrar(@RequestBody @Valid RegisterForm form) {

        Usuario usuario = new Usuario(form);
        Usuario save = repository.save(usuario);

        return save != null ?
                ResponseEntity.ok().build() :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
