package com.backend.agendacrista.demo.service;

import com.backend.agendacrista.demo.error.ResourceNotFoundException;
import com.backend.agendacrista.demo.error.UserPricipalNotAutorizedException;
import com.backend.agendacrista.demo.repository.CidadeRepository;
import com.backend.agendacrista.demo.repository.IgrejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IgrejaService {
    @Autowired
    IgrejaRepository igrejaRepository;
    @Autowired
    CidadeRepository cidadeRepository;

    public void verificaSeIdIgrejaExiste(Long id) {
        if (igrejaRepository.findById(id).isEmpty())
            throw new ResourceNotFoundException("Igreja não encontrada!");
    }

    public void verificaSeIdCidadeExiste(Long id) {
        if (cidadeRepository.findById(id).isEmpty())
            throw new ResourceNotFoundException("Id Cidade inválida!");
    }

    public void verificaSeUsuarioLogadoAutorIgreja(Long idIgreja) {
        if (igrejaRepository.getOne(idIgreja).getUsuario().getId() != UserService.getIdUsuarioLogado())
            throw new UserPricipalNotAutorizedException("Usuário não tem permição");
    }

}
