package dao.controle;

import anotacao.RecuperaConjunto;
import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaPrimeiro;
import dao.impl.JpaDaoGenerico;
import excecao.InfraestruturaException;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class InterceptadorDeDAO implements MethodInterceptor {
    public Object intercept(Object objeto, Method metodo, Object[] args, MethodProxy metodoOriginal) throws Throwable {
        System.out.println("Método interceptado: " + metodo.getName() + " da classe " + metodo.getDeclaringClass().getName());

        JpaDaoGenerico<?, ?> daoGenerico = (JpaDaoGenerico<?, ?>) objeto;

        if (metodo.isAnnotationPresent(RecuperaLista.class)) {
            return daoGenerico.buscaLista(metodo, args);
        } else if (metodo.isAnnotationPresent(RecuperaObjeto.class)) {
            return daoGenerico.busca(metodo, args);
        } else if (metodo.isAnnotationPresent(RecuperaPrimeiro.class)) {
            return daoGenerico.buscaPrimeiro(metodo, args);
        } else if (metodo.isAnnotationPresent(RecuperaConjunto.class)) {
            return daoGenerico.buscaConjunto(metodo, args);
        } else {
            throw new InfraestruturaException("O método " + metodo.getName() + " da classe " + metodo.getDeclaringClass() + " não foi anotado");
        }
    }
}
