package servico.impl;

import dao.MontagemDAO;
import excecao.MontagemNaoEncontradaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Montagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import servico.MontagemAppService;

import java.util.List;

public class MontagemAppServiceImpl implements MontagemAppService {
    @Autowired
    private MontagemDAO montagemDAO;

    @Transactional
    public long inclui(Montagem montagem) {
        montagemDAO.inclui(montagem);
        return montagem.getId();
    }

    @Transactional
    public void altera(Montagem montagem) throws MontagemNaoEncontradaException {
        try {
            montagemDAO.getComLock(montagem.getId());
            montagemDAO.altera(montagem);
        } catch (ObjetoNaoEncontradoException e) {
            throw new MontagemNaoEncontradaException();
        }
    }

    @Transactional
    public void exclui(long id) throws MontagemNaoEncontradaException {
        try {
            Montagem montagem = montagemDAO.get(id);
            montagemDAO.exclui(montagem);
        } catch (ObjetoNaoEncontradoException e) {
            throw new MontagemNaoEncontradaException();
        }
    }

    public Montagem getMontagem(long id) throws MontagemNaoEncontradaException {
        try {
            return montagemDAO.get(id);
        } catch (ObjetoNaoEncontradoException e) {
            throw new MontagemNaoEncontradaException();
        }
    }

    public List<Montagem> getMontagens() {
        return montagemDAO.getTodas();
    }
}
