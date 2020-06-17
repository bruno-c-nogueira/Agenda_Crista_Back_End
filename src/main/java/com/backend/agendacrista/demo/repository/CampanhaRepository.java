package com.backend.agendacrista.demo.repository;

import com.backend.agendacrista.demo.model.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampanhaRepository extends JpaRepository<Campanha, Long> {
}
