package com.pccontrutor.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "usuarios", uniqueConstraints = {
        @UniqueConstraint(name = "UniqueEmail", columnNames = "email")
})
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

    @OneToMany(mappedBy = "usuario")
    @JsonIdentityInfo( // Permite referenciar o usuario apenas pelo id, para evitar dados repetidos no get
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id"
    )
    @JsonIdentityReference(alwaysAsId = true)
    private List<Montagem> montagens;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
}
