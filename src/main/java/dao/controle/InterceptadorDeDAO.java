package dao.controle;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import anotacao.PersistenceContext;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import servico.controle.JPAUtil;

public class InterceptadorDeDAO implements MethodInterceptor {
    public Object intercept(Object objeto, Method metodo, Object[] args, MethodProxy metodoOriginal) throws Throwable {
        Field[] campos = objeto.getClass().getSuperclass().getDeclaredFields();
        for (Field campo : campos) {
            if (campo.isAnnotationPresent(PersistenceContext.class)) {
                campo.setAccessible(true);
                try {
                    campo.set(objeto, JPAUtil.getEntityManager());
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("Método interceptado: " + metodo.getName() + " da classe " + metodo.getDeclaringClass().getName());


        return metodoOriginal.invokeSuper(objeto, args);
    }
}
