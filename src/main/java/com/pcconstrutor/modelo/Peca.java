package com.pcconstrutor.modelo;

import com.pcconstrutor.Console;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "peca")
public class Peca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O 'nome' deve ser informado.")
    private String nome;

    @NotEmpty(message = "A 'marca' deve ser informada.")
    private String marca;

    @NotNull(message = "O 'preco' deve ser informado.")
    private BigDecimal preco;

    @NotEmpty(message = "O 'tipo' deve ser informado.")
    private String tipo;

    @NotEmpty(message = "A 'descricao' deve ser informada.")
    private String descricao;

    private String imagem;

    public Peca() {

    }

    public Peca(String nome, String marca, BigDecimal preco, String tipo, String descricao, String imagem) {
    	this.nome = nome;
    	this.marca = marca;
    	this.preco = preco;
    	this.tipo = tipo;
    	this.descricao = descricao;
        	this.imagem = imagem;
    }

    @Override
    @Transient
    public String toString() {
    	return Console.stringBlock(
            "Peca {",
            "id=" + id,
            "nome='" + nome + "'",
            "marca='" + marca + "'",
            "preco='" + preco + "'",
            "tipo='" + tipo + "'",
            "descricao='" + descricao + "'",
            "imagem='" + imagem + "'",
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

    public String getMarca() {
    	return marca;
    }

    public void setMarca(String marca) {
    	this.marca = marca;
    }

    public BigDecimal getPreco() {
    	return preco;
    }

    public void setPreco(BigDecimal preco) {
    	this.preco = preco;
    }

    public String getTipo() {
    	return tipo;
    }

    public void setTipo(String tipo) {
    	this.tipo = tipo;
    }

    public String getDescricao() {
    	return descricao;
    }

    public void setDescricao(String descricao) {
    	this.descricao = descricao;
    }

    public String getImagem() {
    	return imagem;
    }

    public void setImagem(String imagem) {
    	this.imagem = imagem;
    }
}
