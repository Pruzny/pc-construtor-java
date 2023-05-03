package com.pcconstrutor.exception;

public class UsuarioNaoEncontradoException extends Exception {
    public UsuarioNaoEncontradoException() {
        super("Usuário não encontrado.");
    }
}
