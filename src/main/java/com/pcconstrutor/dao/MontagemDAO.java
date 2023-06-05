package com.pcconstrutor.dao;

import com.pcconstrutor.exception.EstadoDeObjetoObsoletoException;
import com.pcconstrutor.exception.MontagemNaoEncontradaException;
import com.pcconstrutor.model.Montagem;

import java.util.List;

public interface MontagemDAO {
    void inclui(Montagem umaMontagem);

    void altera(Montagem umaMontagem) throws MontagemNaoEncontradaException, EstadoDeObjetoObsoletoException;

    void exclui(Long id) throws MontagemNaoEncontradaException;

    Montagem recuperaUmaMontagem(Long id) throws MontagemNaoEncontradaException;

    List<Montagem> recuperaMontagens();
}
