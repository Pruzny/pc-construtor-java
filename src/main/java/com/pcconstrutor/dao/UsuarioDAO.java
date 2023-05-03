package com.pcconstrutor.dao;

import com.pcconstrutor.exception.UsuarioNaoEncontradoException;
import com.pcconstrutor.model.Usuario;

import java.util.List;

public interface UsuarioDAO {
    void inclui(Usuario umUsuario);

    void altera(Usuario umUsuario) throws UsuarioNaoEncontradoException;

    void exclui(Long id) throws UsuarioNaoEncontradoException;

    Usuario recuperaUmUsuario(String email) throws UsuarioNaoEncontradoException;

    List<Usuario> recuperaUsuarios();
}
