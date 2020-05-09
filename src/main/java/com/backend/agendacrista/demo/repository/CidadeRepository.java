package com.backend.agendacrista.demo.repository;

import com.backend.agendacrista.demo.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
    List<Cidade> findByUfId(Integer id);
    List<Cidade> findByUfUf(String uf);
    @Query()
    List<Cidade> findAllByNomeIsStartingWithAndUfUfContains(String inical,String uf);
}
