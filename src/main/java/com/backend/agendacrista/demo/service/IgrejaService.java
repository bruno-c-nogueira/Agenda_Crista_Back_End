package com.backend.agendacrista.demo.service;

import com.backend.agendacrista.demo.controller.form.AtualizaIgrejaForm;
import com.backend.agendacrista.demo.error.ResourceNotFoundException;
import com.backend.agendacrista.demo.error.UserPricipalNotAutorizedException;
import com.backend.agendacrista.demo.model.*;
import com.backend.agendacrista.demo.repository.CidadeRepository;
import com.backend.agendacrista.demo.repository.IgrejaRepository;
import com.backend.agendacrista.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IgrejaService {
    @Autowired
    IgrejaRepository igrejaRepository;

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PushNotificationFCMService pushNotificationFCMService;

    public List<Igreja> igrejasFavoritasPorUsuarioLogado() {
        return usuarioRepository.getOne(UsuarioService.getIdUsuarioLogado()).getIgrejasFavoritas();
    }

    public void adicionaIgrejaFavoritaPorId(Long id) {
        Igreja igreja = igrejaRepository.getOne(id);
        verificaSeIgrejaNaoEhFavorito(igreja);
        Usuario usuario = usuarioRepository.getOne(UsuarioService.getIdUsuarioLogado());
        List<Igreja> igrejasFavoritas = usuario.getIgrejasFavoritas();
        igrejasFavoritas.add(igreja);
        usuario.setIgrejasFavoritas(igrejasFavoritas);
    }

    public boolean verificaIgrejaEFavoritada(Long id) {
        List<Igreja> igrejasFavoritas = usuarioRepository.getOne(UsuarioService.getIdUsuarioLogado()).getIgrejasFavoritas();
        return igrejasFavoritas.contains(new Igreja(id));
    }

    public void removeIgrejaFavoritaPorId(Long id) {
        Igreja igreja = igrejaRepository.getOne(id);
        verificaSeIgrejaEhFavorito(igreja);
        Usuario usuario = usuarioRepository.getOne(UsuarioService.getIdUsuarioLogado());
        List<Igreja> igrejasFavoritas = usuario.getIgrejasFavoritas();
        igrejasFavoritas.remove(igreja);
        usuario.setIgrejasFavoritas(igrejasFavoritas);
    }

    public void verificaSeIdIgrejaExiste(Long id) {
        if (igrejaRepository.findById(id).isEmpty())
            throw new ResourceNotFoundException("Id Igreja inválido");
    }

    private void verificaSeIgrejaNaoEhFavorito(Igreja igreja) {
        Usuario usuario = usuarioRepository.getOne(UsuarioService.getIdUsuarioLogado());
        if (usuario.getIgrejasFavoritas().contains(igreja))
            throw new UnsupportedOperationException("Igreja ja é favorita");
    }

    private void verificaSeIgrejaEhFavorito(Igreja igreja) {
        Usuario usuario = usuarioRepository.getOne(UsuarioService.getIdUsuarioLogado());
        if (!usuario.getIgrejasFavoritas().contains(igreja))
            throw new UnsupportedOperationException("Igreja não é favorita");
    }

    public void verificaSeUsuarioLogadoAutorIgreja(Long idIgreja) {
        if (igrejaRepository.getOne(idIgreja).getUsuario().getId() != UsuarioService.getIdUsuarioLogado())
            throw new UserPricipalNotAutorizedException("Usuário não tem permição");
    }

    public void alteraStausIgreja(Long idIgreja, StatusIgreja statusIgreja) {
        verificaSeIdIgrejaExiste(idIgreja);
        Igreja igreja = igrejaRepository.getOne(idIgreja);
        igreja.setStatusIgreja(statusIgreja);
        enviaMensagemStatus(idIgreja, statusIgreja);
    }

    public void enviaMensagemStatus(Long idIgreja, StatusIgreja statusIgreja) {
        Igreja igreja = igrejaRepository.getOne(idIgreja);
        String body = statusIgreja == StatusIgreja.VERIFICADO ? "Ola, sua igreja foi verificada! Acesse o aplicativo e adicione novos eventos!" : "Algum dado que você enviou sobre a Igreja pode estar inconsistente com nossa plataforma. Dúvidas entre em contato pelo email.";
        PushFcmAbstract pushFcmAbstract = new PushFcmTo(igreja.getUsuario().getTokenFcm(), new PushFcmNotification(igreja.getNome(), body));
        pushNotificationFCMService.sendNotification(pushFcmAbstract);
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

    public void deletaIgreja(Long id) {
        Igreja igreja = igrejaRepository.getOne(id);
        usuarioRepository.findAllByIgrejasFavoritasContaining(igreja).forEach(usuario -> usuario.getIgrejasFavoritas().remove(igreja));
        igrejaRepository.deleteById(id);
    }
}
