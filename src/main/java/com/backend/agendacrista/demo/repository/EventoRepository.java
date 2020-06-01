package com.backend.agendacrista.demo.repository;

import com.backend.agendacrista.demo.model.DiaDaSemana;
import com.backend.agendacrista.demo.model.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface EventoRepository extends JpaRepository<Evento, Long> {

    Page<Evento> findByIgrejaId(Long id, Pageable pageable);

    @Query(value = "SELECT * FROM evento e Where e.data_final >= :endDate OR e.data_final IS NULL AND e.igreja_id = :id", nativeQuery = true)
    List<Evento> findAllByIgrejaIdIs(LocalDate endDate, Long id);

    List<Evento> findByDataFinalLessThanEqualAndIgrejaId(LocalDate endDate, Long id);

    List<Evento> findByDataFinalGreaterThanEqualOrDataFinalNullAndIgrejaIdAndHorariosDiaDaSemana(LocalDate endDate, Long id, DiaDaSemana diaDaSemana);

    List<Evento> findByHorariosDiaDaSemana(DiaDaSemana diasDaSemana);

}

