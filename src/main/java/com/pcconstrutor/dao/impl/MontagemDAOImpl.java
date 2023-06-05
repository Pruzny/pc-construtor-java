package com.pcconstrutor.dao.impl;

import com.pcconstrutor.dao.MontagemDAO;
import com.pcconstrutor.exception.EstadoDeObjetoObsoletoException;
import com.pcconstrutor.exception.MontagemNaoEncontradaException;
import com.pcconstrutor.model.Montagem;
import com.pcconstrutor.util.FabricaDeEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import java.util.List;

public class MontagemDAOImpl implements MontagemDAO {
    public void inclui(Montagem umaMontagem) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();
            tx = em.getTransaction();
            tx.begin();

            em.persist(umaMontagem);

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

    public void altera(Montagem umaMontagem) throws MontagemNaoEncontradaException, EstadoDeObjetoObsoletoException {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Montagem montagem = em.find(Montagem.class, umaMontagem.getId(), LockModeType.PESSIMISTIC_WRITE);

            if (montagem == null) {
                tx.rollback();
                throw new MontagemNaoEncontradaException();
            }

            em.merge(umaMontagem);

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

    public void exclui(Long id) throws MontagemNaoEncontradaException {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Montagem montagem = em.find(Montagem.class, id, LockModeType.PESSIMISTIC_WRITE);

            if (montagem == null) {
                tx.rollback();
                throw new MontagemNaoEncontradaException();
            }

            em.remove(montagem);

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

    public Montagem recuperaUmaMontagem(Long id) throws MontagemNaoEncontradaException {
        EntityManager em = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();

            Montagem montagem = em.find(Montagem.class, id);

            if (montagem == null) {
                throw new MontagemNaoEncontradaException();
            }

            return montagem;

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Montagem> recuperaMontagens() {
        EntityManager em = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();

            return em.createQuery("SELECT m FROM Montagem m", Montagem.class).getResultList();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
