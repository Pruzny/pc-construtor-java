package excecao;

public class MontagemNaoEncontradaException extends Exception {
	public MontagemNaoEncontradaException() {
		super("Montagem não encontrada!");
	}

	public MontagemNaoEncontradaException(String msg) {
		super(msg);
	}
}