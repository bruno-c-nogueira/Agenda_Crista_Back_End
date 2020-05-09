package com.backend.agendacrista.demo.repository;

import com.backend.agendacrista.demo.model.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventoRepository extends JpaRepository<Evento, Long> {

    Page<Evento> findByCidadeId(Integer id, Pageable pageable);

    Page<Evento> findByIgrejaId(Long id, Pageable pageable);
}
