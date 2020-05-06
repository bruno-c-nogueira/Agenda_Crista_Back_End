package com.backend.agendacrista.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    private String imagem_url;

    @ManyToOne
    private Igreja igreja;

    private String nomeCelebrante;

    @ManyToOne
    private Cidade cidade;

    private LocalDateTime data;


}
