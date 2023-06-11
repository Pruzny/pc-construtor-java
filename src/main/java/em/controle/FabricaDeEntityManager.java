package em.controle;

import net.sf.cglib.proxy.Enhancer;

import javax.persistence.EntityManager;

public class FabricaDeEntityManager {
    public static EntityManager getEntityManager() {
        return (EntityManager) Enhancer.create(EntityManager.class, new InterceptadorDeEntityManager());
    }
}
