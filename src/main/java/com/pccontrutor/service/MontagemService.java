package com.pccontrutor.service;

import com.pccontrutor.exception.EntidadeNaoEncontradaException;
import com.pccontrutor.model.Montagem;
import com.pccontrutor.repository.MontagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MontagemService {
    @Autowired
    private MontagemRepository montagemRepository;

    public Montagem get(Long id) {
        return montagemRepository.findById(id).orElseThrow(()-> new EntidadeNaoEncontradaException(
            (id == 0 ? "Montagem não encontrada." : "Montagem número " + id + " não encontrada."))
        );
    }

    public List<Montagem> getTodas() {
        return montagemRepository.findAll(Sort.by("id"));
    }

    public List<Montagem> getPorUsuario(Long id) {
        return montagemRepository.getPorUsuario(id);
    }

    public Montagem cadastrar(Montagem montagem) {
        return montagemRepository.save(montagem);
    }

    public Montagem atualiza(Montagem montagem) {
        // Precisa usar lock?
        return montagemRepository.save(montagem);
    }

    public void remover(Long id) {
        montagemRepository.deleteById(id);
    }

    public Montagem remover(Montagem montagem) {
        Montagem outraMontagem = get(montagem.getId());
        montagemRepository.deleteById(outraMontagem.getId());
        return outraMontagem;
    }

    public Page<Montagem> getPaginadas(String nome, Pageable pageable) {
        return montagemRepository.getPaginada(nome, pageable);
    }
}
