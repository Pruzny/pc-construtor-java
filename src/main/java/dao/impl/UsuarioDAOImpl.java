package dao.impl;

import dao.UsuarioDAO;
import modelo.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public abstract class UsuarioDAOImpl extends JpaDaoGenerico<Usuario, Long> implements UsuarioDAO {
    public UsuarioDAOImpl() {
        super(Usuario.class);
    }
}
