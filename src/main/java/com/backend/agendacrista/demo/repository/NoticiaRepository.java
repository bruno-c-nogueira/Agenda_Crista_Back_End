package com.backend.agendacrista.demo.repository;

import com.backend.agendacrista.demo.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
}
