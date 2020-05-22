package com.backend.agendacrista.demo.repository;

import com.backend.agendacrista.demo.model.Horarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorariosRepository extends JpaRepository<Horarios, Long> {
}
