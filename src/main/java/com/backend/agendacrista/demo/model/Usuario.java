package com.backend.agendacrista.demo.model;

import com.backend.agendacrista.demo.controller.form.RegisterForm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario extends AbstractEntity implements UserDetails {
    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;
    private boolean ativo = true;
    private LocalDateTime criadoEm = LocalDateTime.now();
    private LocalDateTime verificadoEm;
    private String tokenFcm;
    @OneToOne
    private Endereco endereco;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfils = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Igreja> igrejasFavoritas = new ArrayList<>();

    public Usuario(RegisterForm form) {
        this.nome = form.getNome();
        this.email = form.getEmail();
        this.senha = form.getSenha();
    }

    public Usuario() {
    }

    public Usuario(Long idUsuario) {
        this.setId(idUsuario);
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getVerificadoEm() {
        return verificadoEm;
    }

    public void setVerificadoEm(LocalDateTime verificadoEm) {
        this.verificadoEm = verificadoEm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Igreja> getIgrejasFavoritas() {
        return igrejasFavoritas;
    }

    public void setIgrejasFavoritas(List<Igreja> igrejasFavoritas) {
        this.igrejasFavoritas = igrejasFavoritas;
    }

    public String getTokenFcm() {
        return tokenFcm;
    }

    public void setTokenFcm(String tokenFCM) {
        this.tokenFcm = tokenFCM;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfils;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}