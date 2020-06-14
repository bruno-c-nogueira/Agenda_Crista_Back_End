package com.backend.agendacrista.demo.service;

import com.backend.agendacrista.demo.model.Igreja;
import com.backend.agendacrista.demo.model.Usuario;
import com.backend.agendacrista.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public static Long getIdUsuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) auth.getPrincipal();
        return usuario.getId();
    }

    public List<Igreja> listaIgrejasFavoritasPorUsuarioLogado() {
        return usuarioRepository.getOne(UsuarioService.getIdUsuarioLogado()).getIgrejasFavoritas();
    }

    public void deletaIgrejaFavoritaDosUsuarios(Igreja igreja) {
        usuarioRepository.findAllByIgrejasFavoritasContaining(igreja).forEach(usuario -> usuario.getIgrejasFavoritas().remove(igreja));
    }

}
