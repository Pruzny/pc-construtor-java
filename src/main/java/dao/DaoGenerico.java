package dao;

import excecao.ObjetoNaoEncontradoException;

import java.io.Serializable;

public interface DaoGenerico<T, PK extends Serializable> {
    T inclui(T objeto);

    void altera(T objeto);

    void exclui(T objeto);

    T get(PK id) throws ObjetoNaoEncontradoException;

    T getComLock(PK id) throws ObjetoNaoEncontradoException;
}
