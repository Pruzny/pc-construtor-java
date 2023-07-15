package com.pcconstrutor.modelo;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.pcconstrutor.Console.stringBlock;

@Entity
@Table(name = "montagem")
public class Montagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @NotNull(message = "O 'usuario' deve ser informado.")
    private Usuario usuario;

    @ManyToOne
    @NotNull(message = "O 'gabinete' deve ser informado.")
    private Peca gabinete;

    @ManyToOne
    @NotNull(message = "A 'fonte' deve ser informada.")
    private Peca fonte;

    @ManyToOne
    @NotNull(message = "A 'placaMae' deve ser informada.")
    private Peca placaMae;

    @ManyToOne
    @NotNull(message = "O 'processador' deve ser informado.")
    private Peca processador;

    @ManyToOne
    @NotNull(message = "A 'placaDeVideo' deve ser informada.")
    private Peca placaDeVideo;

    @ManyToOne
    @NotNull(message = "O 'armazenamento' deve ser informado.")
    private Peca armazenamento;

    @ManyToOne
    @NotNull(message = "A 'memoriaRam' deve ser informada.")
    private Peca memoriaRam;

    @ManyToOne
    @NotNull(message = "O 'coolerProcessador' deve ser informado.")
    private Peca coolerProcessador;

    @ManyToOne
    @NotNull(message = "O 'coolerGabinete' deve ser informado.")
    private Peca coolerGabinete;


    // select c from Categoria c left outer join fetch c.produtos where c.id = :id
//    @OneToMany(mappedBy = "categoria")
//    private List<Produto> produtos = new ArrayList<>();

    public Montagem() {

    }

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

    @Override
    @Transient
    public String toString() {
        return stringBlock(
            "Montagem {",
            "id: " + id,
            "nome: '" + nome + "'",
            "usuario: " + usuario,
            "gabinete: " + gabinete,
            "fonte: " + fonte,
            "placaMae: " + placaMae,
            "processador: " + processador,
            "placaDeVideo: " + placaDeVideo,
            "armazenamento: " + armazenamento,
            "memoriaRam: " + memoriaRam,
            "coolerProcessador: " + coolerProcessador,
            "coolerGabinete: " + coolerGabinete,
            "}"
        );
    }

    @Transient
    public String toStringId() {
        return stringBlock(
            "Montagem {",
            "id: " + id,
            "nome: '" + nome + "'",
            "usuario: " + usuario.getId(),
            "gabinete: " + gabinete.getId(),
            "fonte: " + fonte.getId(),
            "placaMae: " + placaMae.getId(),
            "processador: " + processador.getId(),
            "placaDeVideo: " + placaDeVideo.getId(),
            "armazenamento: " + armazenamento.getId(),
            "memoriaRam: " + memoriaRam.getId(),
            "coolerProcessador: " + coolerProcessador.getId(),
            "coolerGabinete: " + coolerGabinete.getId(),
            "}"
        );
    }

    @Transient
    public String toStringNome() {
        return stringBlock(
            "Montagem {",
            "id: " + id,
            "nome: '" + nome + "'",
            "usuario: '" + usuario.getNome() + "'",
            "gabinete: '" + gabinete.getNome() + "'",
            "fonte: '" + fonte.getNome() + "'",
            "placaMae: '" + placaMae.getNome() + "'",
            "processador: '" + processador.getNome() + "'",
            "placaDeVideo: '" + placaDeVideo.getNome() + "'",
            "armazenamento: '" + armazenamento.getNome() + "'",
            "memoriaRam: '" + memoriaRam.getNome() + "'",
            "coolerProcessador: '" + coolerProcessador.getNome() + "'",
            "coolerGabinete: '" + coolerGabinete.getNome() + "'",
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

    public Peca getGabinete() {
        return gabinete;
    }

    public void setGabinete(Peca gabinete) {
        this.gabinete = gabinete;
    }

    public Peca getFonte() {
        return fonte;
    }

    public void setFonte(Peca fonte) {
        this.fonte = fonte;
    }

    public Peca getPlacaMae() {
        return placaMae;
    }

    public void setPlacaMae(Peca placaMae) {
        this.placaMae = placaMae;
    }

    public Peca getProcessador() {
        return processador;
    }

    public void setProcessador(Peca processador) {
        this.processador = processador;
    }

    public Peca getPlacaDeVideo() {
        return placaDeVideo;
    }

    public void setPlacaDeVideo(Peca placadeVideo) {
        this.placaDeVideo = placadeVideo;
    }

    public Peca getArmazenamento() {
        return armazenamento;
    }

    public void setArmazenamento(Peca armazenamento) {
        this.armazenamento = armazenamento;
    }

    public Peca getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(Peca memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public Peca getCoolerProcessador() {
        return coolerProcessador;
    }

    public void setCoolerProcessador(Peca coolerProcessador) {
        this.coolerProcessador = coolerProcessador;
    }

    public Peca getCoolerGabinete() {
        return coolerGabinete;
    }

    public void setCoolerGabinete(Peca coolerGabinete) {
        this.coolerGabinete = coolerGabinete;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
