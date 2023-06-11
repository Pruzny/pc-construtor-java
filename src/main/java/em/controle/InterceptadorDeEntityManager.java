package em.controle;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import servico.controle.JPAUtil;

import java.lang.reflect.Method;

public class InterceptadorDeEntityManager implements MethodInterceptor {
    public Object intercept(Object objeto, Method metodo, Object[] args, MethodProxy metodoOriginal) throws Throwable {
        System.out.println("M�todo interceptado: " + metodo.getName() + " da classe " + metodo.getDeclaringClass().getName());

        // Executa o mesmo m�todo, mas de outro objeto (nesse caso do Entity Manager �nico)
        return metodo.invoke(JPAUtil.getEntityManager(), args);
    }
}