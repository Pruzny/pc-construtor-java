package com.pcconstrutor.dao;

import com.pcconstrutor.exception.ComposicaoNaoEncontradaException;
import com.pcconstrutor.exception.EstadoDeObjetoObsoletoException;
import com.pcconstrutor.model.Composicao;

import java.util.List;

public interface ComposicaoDAO {
    void inclui(Composicao umaComposicao);

    void altera(Composicao umaComposicao) throws ComposicaoNaoEncontradaException, EstadoDeObjetoObsoletoException;

    void exclui(Long id) throws ComposicaoNaoEncontradaException;

    Composicao recuperaUmaComposicao(Long id) throws ComposicaoNaoEncontradaException;

    List<Composicao> recuperaComposicoes();
}
