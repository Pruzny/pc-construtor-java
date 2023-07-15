package com.pccontrutor.service;

import com.pccontrutor.model.Montagem;
import com.pccontrutor.model.Peca;
import com.pccontrutor.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PecaService {
    @Autowired
    private PecaRepository pecaRepository;

    public List<Peca> getTodas() {
        return pecaRepository.findAll(Sort.by("id"));
    }

    public Peca cadastrar(Peca peca) {
        return pecaRepository.save(peca);
    }

    public Peca get(Long id) {
        return pecaRepository.findById(id).orElseThrow(() -> new RuntimeException("Peça número " + id + " não encontrada."));
    }

    public List<Peca> getPorTipo(String tipo) {
        return pecaRepository.getPorTipo(tipo);
    }

    public Peca atualiza(Peca peca) {
        // Precisa usar lock?
        return pecaRepository.save(peca);
    }

    public void remover(Long id) {
        pecaRepository.deleteById(id);
    }

    public Peca remover(Peca peca) {
        Peca outraPeca = get(peca.getId());
        pecaRepository.deleteById(outraPeca.getId());
        return outraPeca;
    }

    public Page<Peca> getPaginadas(String nome, Pageable pageable) {
        return pecaRepository.getPaginada(nome, pageable);
    }
}
