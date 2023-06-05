package com.pcconstrutor.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Peca {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private String tipo;
    private String descricao;

    public Peca() {}

    public Peca(Long id, String nome, BigDecimal preco, String tipo, String descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return String.format("""
                Usuário {
                    id = %d
                    nome = %s
                    preco = %s
                    tipo = %s
                    descricao = %s
                }
                """, this.id, this.nome, this.preco, this.tipo, this.descricao);
    }

    @Id
    @Column
    public Long getId() {
        return id;
    }

    @Column
    public String getNome() {
        return nome;
    }

    @Column
    public BigDecimal getPreco() {
        return preco;
    }

    @Column
    public String getTipo() {
        return tipo;
    }

    @Column
    public String getDescricao() {
        return descricao;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Transient
    public boolean checarCompatibilidade() {
        return false;
    }
}
