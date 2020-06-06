package com.backend.agendacrista.demo.repository;

import com.backend.agendacrista.demo.model.DiaDaSemana;
import com.backend.agendacrista.demo.model.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface EventoRepository extends JpaRepository<Evento, Long> {

    Page<Evento> findByIgrejaId(Long id, Pageable pageable);

    List<Evento> findByDataFinalGreaterThanEqualOrDataFinalNullAndIgrejaId(LocalDate endDate, Long id);

    List<Evento> findByDataFinalLessThanEqualAndIgrejaId(LocalDate endDate, Long id);

    List<Evento> findByDataFinalGreaterThanEqualOrDataFinalNullAndIgrejaIdAndHorariosDiaDaSemana(LocalDate endDate, Long id, DiaDaSemana diaDaSemana);

}

