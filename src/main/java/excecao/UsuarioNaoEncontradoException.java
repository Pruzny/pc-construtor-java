package excecao;

public class UsuarioNaoEncontradoException extends Exception {
	private final static long serialVersionUID = 1;

	public UsuarioNaoEncontradoException() {
		super("Usuário não encontrado!");
	}

	public UsuarioNaoEncontradoException(String msg) {
		super(msg);
	}
}