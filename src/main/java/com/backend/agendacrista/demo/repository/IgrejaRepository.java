package com.backend.agendacrista.demo.repository;

import com.backend.agendacrista.demo.model.Igreja;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IgrejaRepository extends JpaRepository<Igreja, Long> {
    Page<Igreja> findByNomeIgnoreCaseContaining(String nome, Pageable pageable);

    Page<Igreja> findByEndereco_CidadeId(Integer id, Pageable pageable);
}
