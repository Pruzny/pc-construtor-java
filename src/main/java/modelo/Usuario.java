package modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "Usuario.getPorEmail",
                query = "SELECT u FROM Usuario u WHERE u.email = ?1"
        ),
        @NamedQuery(
                name = "Usuario.getTodos",
                query = "SELECT u FROM Usuario u ORDER BY u.id"
        ),
        @NamedQuery(
                name = "Usuario.getComMontagens",
                query = "SELECT u FROM Usuario u LEFT OUTER JOIN FETCH u.montagens WHERE u.email = ?1"
        ),
        @NamedQuery(
                name = "Usuario.getTodosComMontagens",
                query = "SELECT u FROM Usuario u LEFT OUTER JOIN FETCH u.montagens ORDER BY u.id"
        )
})

@Entity
@Table(name = "usuario")
public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha;

    private List<Montagem> montagens = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    @Override
    @Transient
    public String toString() {
        return String.format("\nUsuário {\n\tid = %d\n\tnome = %s\n\temail = %s\n}", this.id, this.nome, this.email);
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

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    @Column(name = "senha")
    public String getSenha() {
        return senha;
    }

    @OneToMany(mappedBy = "usuario")
    @OrderBy
    public List<Montagem> getMontagens() {
        return montagens;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setMontagens(List<Montagem> montagens) {
        this.montagens = montagens;
    }
}
