package dao;

import anotacao.RecuperaLista;
import modelo.Montagem;

import java.util.List;

public interface MontagemDAO extends DaoGenerico<Montagem, Long> {
    @RecuperaLista
    List<Montagem> getTodas();
}
