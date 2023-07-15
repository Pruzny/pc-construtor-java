package com.pcconstrutor.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import static com.pcconstrutor.Console.stringBlock;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O 'nome' deve ser informado.")
    private String nome;

    @NotEmpty(message = "O 'email' deve ser informado.")
    private String email;

    @NotEmpty(message = "A 'senha' deve ser informada.")
    private String senha;

    public Usuario() {

    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    @Override
    @Transient
    public String toString() {
        return stringBlock(
            "Usuario {",
            "id=" + id,
            "nome='" + nome + "'",
            "email='" + email + "'",
            "senha='" + senha + "'",
            "}"
        );
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
