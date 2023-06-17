package com.pcconstrutor.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Composicao {
    private Long id;
    private List<Peca> itens;

    private Integer quantidade;

    public Composicao() {}

    public Composicao(Long id, Integer quantidade) {
        this.id = id;
        this.itens = new ArrayList<>();
        this.quantidade = quantidade;
    }

    @Id
    @Column
    public Long getId() {
        return id;
    }

    // Fetch lazy só carrega quando precisa
    // Cascade remove remove a composição se a peça é deletada
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "composicao_peca",
            joinColumns = @JoinColumn(name = "composicao_id"),
            inverseJoinColumns = @JoinColumn(name = "peca_id")
    )
    @Column
    public List<Peca> getItens() {
        return this.itens;
    }

    @Column
    public Integer getQuantidade() {
        return quantidade;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
        this.id = id;
    }

    public void setItens(List<Peca> itens) {
        this.itens = itens;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
