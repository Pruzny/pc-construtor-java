package dao.controle;

import anotacao.Autowired;
import net.sf.cglib.proxy.Enhancer;
import org.reflections.Reflections;
import servico.controle.InterceptadorDeServico;

import java.lang.reflect.Field;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

public class FabricaDeDAOs {

	@SuppressWarnings("unchecked")
	public static <T> T getDAO(Class<T> tipo) {

		Reflections reflections = new Reflections("dao.impl");

		Set<Class<? extends T>> classes = reflections.getSubTypesOf(tipo);

		if (classes.size() > 1)
			throw new RuntimeException("Somente uma classe pode implementar " + tipo.getName());

		Class<?> classe = classes.iterator().next();

		return (T) Enhancer.create(classe, new InterceptadorDeDAO());
	}
}
