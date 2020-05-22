package com.backend.agendacrista.demo.service;

import com.backend.agendacrista.demo.error.ResourceNotFoundException;
import com.backend.agendacrista.demo.repository.CidadeRepository;
import com.backend.agendacrista.demo.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalidadesService {
    @Autowired
    CidadeRepository cidadeRepository;
    @Autowired
    EstadoRepository estadoRepository;

    public void verificaSeIdCidadeExiste(Long id) {
        if (cidadeRepository.findById(id).isEmpty())
            throw new ResourceNotFoundException("Id Cidade inválida");
    }

    public void verificaSeUfExiste(String uf) {
        if (estadoRepository.findByUf(uf).isEmpty())
            throw new ResourceNotFoundException("Uf do estado inválido");
    }
}
