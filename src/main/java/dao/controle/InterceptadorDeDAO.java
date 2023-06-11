package dao.controle;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import anotacao.PersistenceContext;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import servico.controle.JPAUtil;

public class InterceptadorDeDAO implements MethodInterceptor {
    public Object intercept(Object objeto, Method metodo, Object[] args, MethodProxy metodoOriginal) throws Throwable {
        // Verifica��o de anota��o sai do interceptador e vai para ap�s a cria��o do proxy
        System.out.println("M�todo interceptado: " + metodo.getName() + " da classe " + metodo.getDeclaringClass().getName());

        return metodoOriginal.invokeSuper(objeto, args);
    }
}
