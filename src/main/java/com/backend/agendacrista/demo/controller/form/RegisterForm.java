package com.backend.agendacrista.demo.controller.form;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;

public class RegisterForm {
    @NotNull
    @NotNull(message = "campo email é obrigatorio")
    private String email;
    @NotNull@NotNull(message = "campo senha é obrigatorio")
    private String senha;
    @NotNull@NotNull(message = "campo nome é obrigatorio")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(senha);
        return hashedPassword;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
