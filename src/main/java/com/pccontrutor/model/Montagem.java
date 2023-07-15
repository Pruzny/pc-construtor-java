package com.pccontrutor.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Montagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Peca gabinete;

    @ManyToOne
    private Peca fonte;

    @ManyToOne
    private Peca placaMae;

    @ManyToOne
    private Peca processador;

    @ManyToOne
    private Peca placaDeVideo;

    @ManyToOne
    private Peca armazenamento;

    @ManyToOne
    private Peca memoriaRam;

    @ManyToOne
    private Peca coolerProcessador;

    @ManyToOne
    private Peca coolerGabinete;


       // select c from Categoria c left outer join fetch c.produtos where c.id = :id
//    @OneToMany(mappedBy = "categoria")
//    private List<Produto> produtos = new ArrayList<>();

    public Montagem(String nome, Usuario usuario, Peca gabinete, Peca fonte, Peca placaMae, Peca processador, Peca placaDeVideo, Peca armazenamento, Peca memoriaRam, Peca coolerProcessador, Peca coolerGabinete) {
        this.nome = nome;
        this.usuario = usuario;
        this.gabinete = gabinete;
        this.fonte = fonte;
        this.placaMae = placaMae;
        this.processador = processador;
        this.placaDeVideo = placaDeVideo;
        this.armazenamento = armazenamento;
        this.memoriaRam = memoriaRam;
        this.coolerProcessador = coolerProcessador;
        this.coolerGabinete = coolerGabinete;
    }
}
