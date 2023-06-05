package com.pcconstrutor.dao.impl;

import com.pcconstrutor.dao.PecaDAO;
import com.pcconstrutor.exception.EstadoDeObjetoObsoletoException;
import com.pcconstrutor.exception.PecaNaoEncontradaException;
import com.pcconstrutor.model.Peca;
import com.pcconstrutor.util.FabricaDeEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import java.util.List;

public class PecaDAOImpl implements PecaDAO {
    public void inclui(Peca umaPeca) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();
            tx = em.getTransaction();
            tx.begin();

            em.persist(umaPeca);

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

    public void altera(Peca umaPeca) throws PecaNaoEncontradaException, EstadoDeObjetoObsoletoException {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Peca peca = em.find(Peca.class, umaPeca.getId(), LockModeType.PESSIMISTIC_WRITE);

            if (peca == null) {
                tx.rollback();
                throw new PecaNaoEncontradaException();
            }

            em.merge(umaPeca);

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

    public void exclui(Long id) throws PecaNaoEncontradaException {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Peca peca = em.find(Peca.class, id, LockModeType.PESSIMISTIC_WRITE);

            if (peca == null) {
                tx.rollback();
                throw new PecaNaoEncontradaException();
            }

            em.remove(peca);

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

    public Peca recuperaUmaPeca(Long id) throws PecaNaoEncontradaException {
        EntityManager em = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();

            Peca peca = em.find(Peca.class, id);

            if (peca == null) {
                throw new PecaNaoEncontradaException();
            }

            return peca;

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Peca> recuperaPecas() {
        EntityManager em = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();

            return em.createQuery("SELECT p FROM Peca p", Peca.class).getResultList();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
