package com.backend.agendacrista.demo.repository;

import com.backend.agendacrista.demo.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    public List<Estado> findByUf(String uf);
}
