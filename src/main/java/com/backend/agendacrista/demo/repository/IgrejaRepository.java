package com.backend.agendacrista.demo.repository;

import com.backend.agendacrista.demo.model.Igreja;
import com.backend.agendacrista.demo.model.StatusIgreja;
import com.backend.agendacrista.demo.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IgrejaRepository extends JpaRepository<Igreja, Long> {

    Page<Igreja> findByStatusIgreja(StatusIgreja statusIgreja, Pageable pageable);

    List<Igreja> findByUsuario(Usuario usuario);

}
