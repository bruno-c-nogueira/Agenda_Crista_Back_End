package com.backend.agendacrista.demo.controller;

import com.backend.agendacrista.demo.controller.form.RegisterForm;
import com.backend.agendacrista.demo.model.Usuario;
import com.backend.agendacrista.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/register")
public class RegistrarController {

    @Autowired
    UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registrar(@RequestBody @Valid RegisterForm form) {

        Optional<Usuario> existeUsuario = repository.findByEmail(form.getEmail());

        if (existeUsuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email esta sendo usado.");
        }

        Usuario save = repository.save(new Usuario(form));

        return ResponseEntity.ok().build();
    }
}
