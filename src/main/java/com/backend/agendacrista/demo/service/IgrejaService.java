package com.backend.agendacrista.demo.service;

import com.backend.agendacrista.demo.controller.form.AtualizaIgrejaForm;
import com.backend.agendacrista.demo.error.ResourceNotFoundException;
import com.backend.agendacrista.demo.error.UserPricipalNotAutorizedException;
import com.backend.agendacrista.demo.model.Endereco;
import com.backend.agendacrista.demo.model.Igreja;
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
            throw new ResourceNotFoundException("Id Igreja inválido");
    }

    public void verificaSeUsuarioLogadoAutorIgreja(Long idIgreja) {
        if (igrejaRepository.getOne(idIgreja).getUsuario().getId() != UsusarioService.getIdUsuarioLogado())
            throw new UserPricipalNotAutorizedException("Usuário não tem permição");
    }

    public Igreja atualizaIgreja(Long idIgreja, AtualizaIgrejaForm igrejaForm) {
        Igreja igreja = igrejaRepository.getOne(idIgreja);
        igreja.setNome(igrejaForm.getNome());
        igreja.setDescricao(igrejaForm.getDescricao());
        igreja.setImagem_url(igrejaForm.getImagem_url());
        igreja.setTelefone(igrejaForm.getTelefone());
        Endereco endereco = igreja.getEndereco();
        endereco.setRua(igrejaForm.getEndereco().getRua());
        endereco.setNumero(igrejaForm.getEndereco().getNumero());
        endereco.setBairro(igrejaForm.getEndereco().getBairro());
        endereco.setComplemento(igrejaForm.getEndereco().getComplemento());
        endereco.setCidade(cidadeRepository.getOne(igrejaForm.getEndereco().getCidade_id()));
        return igreja;
    }
}
