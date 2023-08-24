package com.projetoteste.projetoteste2023.entity;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "usr_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;
    @Column(name = "usr_nome")
    private String nome;
    @Column(name = "usr_senha")
    private String senha;
    public Usuario() {
        this.autorizacoes = new HashSet<Autorizacao>(); 
     }
    public Usuario(String nome, String senha) {
    this();
    this.nome = nome;
    this.senha = senha;
    }
    public Long getId() {
    return id;
    }
    public void setId(Long id) {
    this.id = id;
    }
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name= "uau_usuario_autorizacao",
        joinColumns = {@JoinColumn(name = "usr_id")},
        inverseJoinColumns = {@JoinColumn(name = "aut_id")}
        )
    private Set<Autorizacao> autorizacoes;
    public Set<Autorizacao> getAutorizacoes() {
        return autorizacoes;
    }
    public void setAutorizacoes(Set<Autorizacao> autorizacoes) {
        this.autorizacoes = autorizacoes;
    }
    public String getNome() {
    return nome;
    }
    public void setNome(String nome) {
    this.nome = nome;
    }
    public String getSenha() {
    return senha;
    }
    public void setSenha(String senha) {
    this.senha = senha;
    }
    
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<Anotacao> anotacoes;
    public Set<Anotacao> getAnotacoes() {
        return anotacoes;
    }
    public void setAnotacoes(Set<Anotacao> anotacoes) {
        this.anotacoes = anotacoes;
    }
    
    

    
}