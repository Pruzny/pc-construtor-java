package excecao;

public class MontagemNaoEncontradaException extends Exception {
	public MontagemNaoEncontradaException() {
		super("Montagem n�o encontrada!");
	}

	public MontagemNaoEncontradaException(String msg) {
		super(msg);
	}
}