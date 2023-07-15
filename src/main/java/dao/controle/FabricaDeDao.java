package dao.controle;

import dao.impl.MontagemDAOImpl;
import dao.impl.UsuarioDAOImpl;
import net.sf.cglib.proxy.Enhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Anota��o indica que a classe possui m�todos com @Bean
@Configuration
public class FabricaDeDao {

    @SuppressWarnings("unchecked")
    public static <T> T getDao(Class<T> classe) throws Exception {
        return (T) Enhancer.create(classe, new InterceptadorDeDAO());
    }

    // @Bean Indica ao Spring que tem uma inst�ncia da classe DAOImpl, que ser� guardada para futuros pedidos
    @Bean
    public static UsuarioDAOImpl getUsuarioDAO() throws Exception {
        return getDao(dao.impl.UsuarioDAOImpl.class);
    }

    @Bean
    public static MontagemDAOImpl getMontagemDAO() throws Exception {
        return getDao(dao.impl.MontagemDAOImpl.class);
    }
}
