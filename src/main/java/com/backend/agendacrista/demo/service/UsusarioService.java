package com.backend.agendacrista.demo.service;

import com.backend.agendacrista.demo.model.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsusarioService {

    public static Long getIdUsuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) auth.getPrincipal();
        return usuario.getId();
    }
}
