package com.pcconstrutor.dao.impl;

import com.pcconstrutor.dao.ComposicaoDAO;
import com.pcconstrutor.exception.ComposicaoNaoEncontradaException;
import com.pcconstrutor.exception.EstadoDeObjetoObsoletoException;
import com.pcconstrutor.model.Composicao;
import com.pcconstrutor.util.FabricaDeEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import java.util.List;

public class ComposicaoDAOImpl implements ComposicaoDAO {
    public void inclui(Composicao umaComposicao) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();
            tx = em.getTransaction();
            tx.begin();

            em.persist(umaComposicao);

            tx.commit();

        } catch (RuntimeException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (RuntimeException ignored) {}
            }
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void altera(Composicao umaComposicao) throws ComposicaoNaoEncontradaException, EstadoDeObjetoObsoletoException {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Composicao composicao = em.find(Composicao.class, umaComposicao.getId(), LockModeType.PESSIMISTIC_WRITE);

            if (composicao == null) {
                tx.rollback();
                throw new ComposicaoNaoEncontradaException();
            }

            em.merge(umaComposicao);

            tx.commit();

        } catch (OptimisticLockException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (RuntimeException ignored) {}
            }
            throw new EstadoDeObjetoObsoletoException();
        } catch (RuntimeException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (RuntimeException ignore) {}
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void exclui(Long id) throws ComposicaoNaoEncontradaException {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Composicao composicao = em.find(Composicao.class, id, LockModeType.PESSIMISTIC_WRITE);

            if (composicao == null) {
                tx.rollback();
                throw new ComposicaoNaoEncontradaException();
            }

            em.remove(composicao);

            tx.commit();

        } catch (RuntimeException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (RuntimeException ignored) {}
            }
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Composicao recuperaUmaComposicao(Long id) throws ComposicaoNaoEncontradaException {
        EntityManager em = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();

            Composicao composicao = em.find(Composicao.class, id);

            if (composicao == null) {
                throw new ComposicaoNaoEncontradaException();
            }

            return composicao;

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Composicao> recuperaComposicoes() {
        EntityManager em = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();

            return em.createQuery("SELECT c FROM Composicao c", Composicao.class).getResultList();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
