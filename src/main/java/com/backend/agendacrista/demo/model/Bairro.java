package com.backend.agendacrista.demo.model;

import javax.persistence.*;

@Entity
public class Bairro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String nome;


}
