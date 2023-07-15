package dao.impl;

import dao.MontagemDAO;
import modelo.Montagem;
import org.springframework.stereotype.Repository;

@Repository
public abstract class MontagemDAOImpl extends JpaDaoGenerico<Montagem, Long> implements MontagemDAO {
    public MontagemDAOImpl() {
        super(Montagem.class);
    }
}
