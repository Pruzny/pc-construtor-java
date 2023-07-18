package com.pccontrutor.repository;

import com.pccontrutor.model.Usuario;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select u from Usuario u where u.id = :id")
    Optional<Usuario> getComLock(@Param("id") Long id);

    @Query("select u from Usuario u left join fetch u.montagens where u.id = :id")
    List<Usuario> getComMontagens();

    @Query("select u from Usuario u where u.email = :email and u.senha = :senha")
    List<Usuario> login(@Param("email") String email, @Param("senha") String senha);

    @Query(
            value = "select u from Usuario u where u.nome like %:nome% order by u.id desc",
            countQuery = "select count(u) from Usuario u where u.nome like %:nome%"
    )
    Page<Usuario> getPaginado(String nome, Pageable pageable);
}
