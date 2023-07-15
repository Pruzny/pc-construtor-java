package servico.impl;

import dao.UsuarioDAO;
import excecao.ObjetoNaoEncontradoException;
import excecao.UsuarioNaoEncontradoException;
import modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import servico.UsuarioAppService;

import java.util.List;

public class UsuarioAppServiceImpl implements UsuarioAppService {
    @Autowired
    private UsuarioDAO usuarioDAO;

    @Transactional
    public long inclui(Usuario usuario) {
        usuarioDAO.inclui(usuario);
        return usuario.getId();
    }

    @Transactional
    public void altera(Usuario usuario) throws UsuarioNaoEncontradoException {
        try {
            usuarioDAO.getComLock(usuario.getId());
            usuarioDAO.altera(usuario);
        } catch (ObjetoNaoEncontradoException e) {
            throw new UsuarioNaoEncontradoException();
        }
    }

    @Transactional
    public void exclui(long id) throws UsuarioNaoEncontradoException {
        try {
            Usuario usuario = usuarioDAO.get(id);
            usuarioDAO.exclui(usuario);
        } catch (ObjetoNaoEncontradoException e) {
            throw new UsuarioNaoEncontradoException();
        }
    }

    public Usuario getUsuario(String email) throws UsuarioNaoEncontradoException {
        try {
            return usuarioDAO.getPorEmail(email);
        } catch (ObjetoNaoEncontradoException e) {
            throw new UsuarioNaoEncontradoException();
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarioDAO.getTodos();
    }

    public Usuario getComMontagens(String email) throws UsuarioNaoEncontradoException {
        try {
            return usuarioDAO.getComMontagens(email);
        } catch (ObjetoNaoEncontradoException e) {
            throw new UsuarioNaoEncontradoException();
        }
    }

    public List<Usuario> getTodosComMontagens() {
        return usuarioDAO.getTodosComMontagens();
    }
}
