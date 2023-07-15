package dao.impl;

import dao.DaoGenerico;
import excecao.ObjetoNaoEncontradoException;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class JpaDaoGenerico<T, PK extends Serializable> implements DaoGenerico<T, PK> {
    private Class<T> tipo;

    @PersistenceContext
    protected EntityManager em;

    public JpaDaoGenerico(Class<T> tipo) {
        this.tipo = tipo;
    }

    public final T inclui(T objeto) {
        em.persist(objeto);
        return objeto;
    }

    public final void altera(T objeto) {
        em.merge(objeto);
    }

    public final void exclui(T objeto) {
        em.remove(objeto);
    }

    public final T get(PK id) throws ObjetoNaoEncontradoException {
        T objeto;
        objeto = em.find(tipo, id);

        if (objeto == null) {
            throw new ObjetoNaoEncontradoException();
        }

        return objeto;
    }

    public final T getComLock(PK id) throws ObjetoNaoEncontradoException {
        T objeto;
        objeto = em.find(tipo, id, LockModeType.PESSIMISTIC_WRITE);

        if (objeto == null) {
            throw new ObjetoNaoEncontradoException();
        }

        return objeto;
    }

    private String getNomeDaBuscaPeloMetodo(Method metodo) {
        return tipo.getSimpleName() + "." + metodo.getName();
    }

    @SuppressWarnings("unchecked")
    public final T busca(Method metodo, Object... argumentos) throws ObjetoNaoEncontradoException {
        T objeto;
        String nomeDaBusca = getNomeDaBuscaPeloMetodo(metodo);

        try {
            Query query = em.createNamedQuery(nomeDaBusca);

//            for (int i = 0; i < argumentos.length; i++) {
//                Object arg = argumentos[i];
//                query.setParameter(i + 1, arg);
//            }

            int i = 1;
            for (Object argumento : argumentos) {
                query.setParameter(i++, argumento);
            }

            objeto = (T) query.getSingleResult();
        } catch (NoResultException e) {
            throw new ObjetoNaoEncontradoException();
        }

        return objeto;
    }

    @SuppressWarnings("unchecked")
    public final T buscaPrimeiro(Method metodo, Object... argumentos) throws ObjetoNaoEncontradoException {
        T objeto;
        String nomeDaBusca = getNomeDaBuscaPeloMetodo(metodo);

        try {
            Query query = em.createNamedQuery(nomeDaBusca);

            int i = 1;
            for (Object argumento : argumentos) {
                query.setParameter(i++, argumento);
            }

            objeto = (T) query.getResultList().get(0);
        } catch (IndexOutOfBoundsException | NoResultException e) {
            throw new ObjetoNaoEncontradoException();
        }

        return objeto;
    }

    @SuppressWarnings("unchecked")
    public final List<T> buscaLista(Method metodo, Object... argumentos) {
        String nomeDaBusca = getNomeDaBuscaPeloMetodo(metodo);

        Query query = em.createNamedQuery(nomeDaBusca);

        int i = 1;
        for (Object argumento : argumentos) {
            query.setParameter(i++, argumento);
        }

        return (List<T>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public final Set<T> buscaConjunto(Method metodo, Object... argumentos) {
        String nomeDaBusca = getNomeDaBuscaPeloMetodo(metodo);

        Query query = em.createNamedQuery(nomeDaBusca);

        int i = 1;
        for (Object argumento : argumentos) {
            query.setParameter(i++, argumento);
        }

        return new LinkedHashSet<T>(query.getResultList());
    }
}
