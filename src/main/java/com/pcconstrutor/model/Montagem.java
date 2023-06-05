package com.pcconstrutor.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Montagem {
    private Long id;
    private Peca gabinete;
    private Peca fonte;
    private Peca placaMae;
    private Peca placaDeVideo;
    private Composicao armazenamento;
    private Composicao memoriaRam;
    private Peca coolerProcessador;
    private Composicao coolers;

    public Montagem() {}

    public Montagem(Long id, Peca gabinete, Peca fonte, Peca placaMae, Peca placaDeVideo, Composicao armazenamento, Composicao memoriaRam, Peca coolerProcessador, Composicao coolers) {
        this.id = id;
        this.gabinete = gabinete;
        this.fonte = fonte;
        this.placaMae = placaMae;
        this.placaDeVideo = placaDeVideo;
        this.armazenamento = armazenamento;
        this.memoriaRam = memoriaRam;
        this.coolerProcessador = coolerProcessador;
        this.coolers = coolers;
    }

    @Id
    public Long getId() {
        return id;
    }

    // Fetch lazy só carrega quando precisa
    // Cascade remove remove a composição se a peça é deletada
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Column
    public Peca getGabinete() {
        return gabinete;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Column
    public Peca getFonte() {
        return fonte;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Column
    public Peca getPlacaMae() {
        return placaMae;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Column
    public Peca getPlacaDeVideo() {
        return placaDeVideo;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Column
    public Composicao getArmazenamento() {
        return armazenamento;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Column
    public Composicao getMemoriaRam() {
        return memoriaRam;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Column
    public Peca getCoolerProcessador() {
        return coolerProcessador;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Column
    public Composicao getCoolers() {
        return coolers;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
        this.id = id;
    }

    public void setGabinete(Peca gabinete) {
        this.gabinete = gabinete;
    }

    public void setFonte(Peca fonte) {
        this.fonte = fonte;
    }

    public void setPlacaMae(Peca placaMae) {
        this.placaMae = placaMae;
    }

    public void setPlacaDeVideo(Peca placaDeVideo) {
        this.placaDeVideo = placaDeVideo;
    }

    public void setArmazenamento(Composicao armazenamento) {
        this.armazenamento = armazenamento;
    }

    public void setMemoriaRam(Composicao memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public void setCoolerProcessador(Peca coolerProcessador) {
        this.coolerProcessador = coolerProcessador;
    }

    public void setCoolers(Composicao coolers) {
        this.coolers = coolers;
    }


}
