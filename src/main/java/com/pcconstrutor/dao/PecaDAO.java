package com.pcconstrutor.dao;

import com.pcconstrutor.exception.EstadoDeObjetoObsoletoException;
import com.pcconstrutor.exception.PecaNaoEncontradaException;
import com.pcconstrutor.model.Peca;

import java.util.List;

public interface PecaDAO {
    void inclui(Peca umaPeca);

    void altera(Peca umaPeca) throws PecaNaoEncontradaException, EstadoDeObjetoObsoletoException;

    void exclui(Long id) throws PecaNaoEncontradaException;

    Peca recuperaUmaPeca(Long id) throws PecaNaoEncontradaException;

    List<Peca> recuperaPecas();
}
