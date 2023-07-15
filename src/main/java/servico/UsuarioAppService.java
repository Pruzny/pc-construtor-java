package servico;

import excecao.UsuarioNaoEncontradoException;
import modelo.Usuario;

import java.util.List;

public interface UsuarioAppService {
    long inclui(Usuario usuario);

    void altera(Usuario usuario) throws UsuarioNaoEncontradoException;

    void exclui(long id) throws UsuarioNaoEncontradoException;

    Usuario getUsuario(String email) throws UsuarioNaoEncontradoException;

    List<Usuario> getUsuarios();

    Usuario getComMontagens(String email) throws UsuarioNaoEncontradoException;

    List<Usuario> getTodosComMontagens();
}
