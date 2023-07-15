package modelo;

import javax.persistence.*;

@Entity
@Table(name = "montagem")
public class Montagem {
    private Long id;
    private String nome;
    private Peca gabinete;
    private Peca fonte;
    private Peca placaMae;
    private Peca processador;
    private Peca placaDeVideo;
    private Peca armazenamento;
    private Peca memoriaRam;
    private Peca coolerProcessador;
    private Peca coolerGabinete;

    private Usuario usuario;

    public Montagem() {
    }

    public Montagem(String nome, Peca gabinete, Peca fonte, Peca placaMae, Peca processador, Peca placaDeVideo, Peca armazenamento, Peca memoriaRam, Peca coolerProcessador, Peca coolerGabinete, Usuario usuario) {
        this.nome = nome;
        this.gabinete = gabinete;
        this.fonte = fonte;
        this.placaMae = placaMae;
        this.processador = processador;
        this.placaDeVideo = placaDeVideo;
        this.armazenamento = armazenamento;
        this.memoriaRam = memoriaRam;
        this.coolerProcessador = coolerProcessador;
        this.coolerGabinete = coolerGabinete;
        this.usuario = usuario;
    }

    @Override
    @Transient
    public String toString() {
        return String.format("\nMontagem {\n\tid = %d\n\tgabinete = %s\n\tfonte = %s\n\tplacaMae = %s\n\tprocessador = %s\n\tplacaDeVideo = %s\n\tarmazenamento = %s\n\tmemoriaRam = %s\n\tcoolerProcessador = %s\n\tcoolerGabinete = %s\n}",
                this.id, this.gabinete, this.fonte, this.placaMae, this.processador, this.placaDeVideo, this.armazenamento, this.memoriaRam, this.coolerProcessador, this.coolerGabinete);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gabinete_id")
    public Peca getGabinete() {
        return gabinete;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fonte_id")
    public Peca getFonte() {
        return fonte;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "placa_mae_id")
    public Peca getPlacaMae() {
        return placaMae;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "processador_id")
    public Peca getProcessador() {
        return processador;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "placa_de_video_id")
    public Peca getPlacaDeVideo() {
        return placaDeVideo;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "armazenamento_id")
    public Peca getArmazenamento() {
        return armazenamento;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "memoria_ram_id")
    public Peca getMemoriaRam() {
        return memoriaRam;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cooler_processador_id")
    public Peca getCoolerProcessador() {
        return coolerProcessador;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cooler_gabinete_id")
    public Peca getCoolerGabinete() {
        return coolerGabinete;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    public Usuario getUsuario() {
        return usuario;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public void setProcessador(Peca processador) {
        this.processador = processador;
    }

    public void setPlacaDeVideo(Peca placaDeVideo) {
        this.placaDeVideo = placaDeVideo;
    }

    public void setArmazenamento(Peca armazenamento) {
        this.armazenamento = armazenamento;
    }

    public void setMemoriaRam(Peca memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public void setCoolerProcessador(Peca coolerProcessador) {
        this.coolerProcessador = coolerProcessador;
    }

    public void setCoolerGabinete(Peca coolerGabinete) {
        this.coolerGabinete = coolerGabinete;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
