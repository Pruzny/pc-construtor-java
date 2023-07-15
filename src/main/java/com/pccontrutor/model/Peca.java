package com.pccontrutor.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Peca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String marca;

    private BigDecimal preco;

    private String tipo;

    private String descricao;

    private String imagem;

    public Peca(String nome, String marca, BigDecimal preco, String tipo, String descricao, String imagem) {
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.tipo = tipo;
        this.descricao = descricao;
        this.imagem = imagem;
    }
}
