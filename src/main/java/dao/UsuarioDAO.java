package dao;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import excecao.ObjetoNaoEncontradoException;
import modelo.Usuario;

import java.util.List;

public interface UsuarioDAO extends DaoGenerico<Usuario, Long> {
    @RecuperaObjeto
    Usuario getPorEmail(String email) throws ObjetoNaoEncontradoException;

    @RecuperaLista
    List<Usuario> getTodos();

    @RecuperaObjeto
    Usuario getComMontagens(String email) throws ObjetoNaoEncontradoException;

    @RecuperaLista
    List<Usuario> getTodosComMontagens();
}
