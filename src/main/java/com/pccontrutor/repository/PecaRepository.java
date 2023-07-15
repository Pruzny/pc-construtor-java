package com.pccontrutor.repository;

import com.pccontrutor.model.Peca;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PecaRepository extends JpaRepository<Peca, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Peca p where p.id = :id")
    Optional<Peca> getComLock(@Param("id") Long id);

    @Query("select p from Peca p where p.tipo = :tipo order by p.id desc")
    List<Peca> getPorTipo(@Param("tipo") String tipo);

    @Query(
        value = "select p from Peca p where p.nome like %:nome% order by p.id desc",
        countQuery = "select count(p) from Peca p where p.nome like %:nome%"
    )
    Page<Peca> getPaginada(String nome, Pageable pageable);
}
