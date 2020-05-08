package com.backend.agendacrista.demo.repository;

import com.backend.agendacrista.demo.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
