package com.pcconstrutor.model;

import javax.persistence.*;

@Entity
@Table
public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {}

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    public Long getId() {
        return this.id;
    }

    @Column
    public String getNome() {
        return this.nome;
    }

    @Column
    public String getEmail() {
        return this.email;
    }

    @Column
    @SuppressWarnings("unused")
    private String getSenha() {
        return this.senha;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Transient
    public Boolean verificarSenha(String senha) {
        return this.senha.equals(senha);
    }
}
