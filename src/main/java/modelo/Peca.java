package modelo;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "peca")
public class Peca {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private String tipo;
    private String descricao;

    public Peca() {
    }

    public Peca(String nome, BigDecimal preco, String tipo, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    @Override
    @Transient
    public String toString() {
        return String.format("\nPeca {\n\tid = %d\n\tnome = %s\n\tpreco = %s\n\ttipo = %s\n\tdescricao = %s\n}", this.id, this.nome, this.preco, this.tipo, this.descricao);
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

    @Column(name = "preco")
    public BigDecimal getPreco() {
        return preco;
    }

    @Column(name = "tipo")
    public String getTipo() {
        return tipo;
    }

    @Column(name = "descricao")
    public String getDescricao() {
        return descricao;
    }

    public void setId(Long id) {
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
}
