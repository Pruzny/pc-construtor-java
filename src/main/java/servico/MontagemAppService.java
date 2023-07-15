package servico;

import excecao.MontagemNaoEncontradaException;
import modelo.Montagem;

import java.util.List;

public interface MontagemAppService {
    long inclui(Montagem montagem);

    void altera(Montagem montagem) throws MontagemNaoEncontradaException;

    void exclui(long id) throws MontagemNaoEncontradaException;

    Montagem getMontagem(long id) throws MontagemNaoEncontradaException;

    List<Montagem> getMontagens();
}
