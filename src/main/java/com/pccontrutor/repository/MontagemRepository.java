package com.pccontrutor.repository;

import com.pccontrutor.model.Montagem;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MontagemRepository extends JpaRepository<Montagem, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select m from Montagem m where m.id = :id")
    Optional<Montagem> getComLock(@Param("id") Long id);

    @Query("select m from Montagem m left join fetch m.usuario where m.usuario.id = :id")
    List<Montagem> getPorUsuario(@Param("id") Long id);

    @Query("select m from Montagem m left join fetch m.usuario where m.usuario.email = :email")
    List<Montagem> getPorUsuario(@Param("email") String email);

    @Query(
        value = "select m from Montagem m where m.nome like %:nome% and m.usuario.id = :usuarioId order by m.id desc",
        countQuery = "select count(m) from Montagem m where m.nome like %:nome% and m.usuario.id = :usuarioId"
    )
    Page<Montagem> getPaginada(String nome, Long usuarioId, Pageable pageable);
}
