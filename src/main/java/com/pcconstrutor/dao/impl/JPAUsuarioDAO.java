package com.pcconstrutor.dao.impl;

import com.pcconstrutor.dao.UsuarioDAO;
import com.pcconstrutor.exception.UsuarioNaoEncontradoException;
import com.pcconstrutor.model.Usuario;
import com.pcconstrutor.util.FabricaDeEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import java.util.List;


public class JPAUsuarioDAO implements UsuarioDAO {
    public void inclui(Usuario umUsuario) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();
            tx = em.getTransaction();
            tx.begin();

            em.persist(umUsuario);

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

    public void altera(Usuario umUsuario) throws UsuarioNaoEncontradoException {
        EntityManager em = null;
        EntityTransaction tx = null;
        Usuario usuario = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();
            tx = em.getTransaction();
            tx.begin();

            usuario = em.find(Usuario.class, umUsuario.getId(), LockModeType.PESSIMISTIC_WRITE);

            if (usuario == null) {
                tx.rollback();
                throw new UsuarioNaoEncontradoException();
            }

            em.merge(umUsuario);

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (RuntimeException ignore) {}
            }
        }
    }

    public void exclui(Long id) throws UsuarioNaoEncontradoException {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Usuario usuario = em.find(Usuario.class, id, LockModeType.PESSIMISTIC_WRITE);

            if (usuario == null) {
                tx.rollback();
                throw new UsuarioNaoEncontradoException();
            }

            em.remove(usuario);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (RuntimeException ignore) {}
            }
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Usuario recuperaUmUsuario(String email) throws UsuarioNaoEncontradoException {
        EntityManager em = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();


            Usuario umUsuario = (Usuario) em.createQuery("SELECT u FROM Usuario u WHERE u.email = '" + email + '\'').getSingleResult();

            if (umUsuario == null) {
                throw new UsuarioNaoEncontradoException();
            }

            return umUsuario;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> recuperaUsuarios() {
        EntityManager em = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();

            @SuppressWarnings("unchecked")
            List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u order by u.id").getResultList();

            return usuarios;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
