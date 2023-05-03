package com.pcconstrutor.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class FabricaDeDAOs {
    private static final ResourceBundle prop;

    static {
        try {
            prop = ResourceBundle.getBundle("dao");
        } catch (MissingResourceException e) {
            System.out.println("Arquivo dao.properties não encontrado");
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getDAO(Class<T> tipo) {
        T dao;
        String nomeDaClasse = null;

        try {
            nomeDaClasse = prop.getString(tipo.getSimpleName());
            dao = (T) Class.forName(nomeDaClasse).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("Não foi possível criar um objeto do tipo " + nomeDaClasse);
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.printf("Classe %s não encontrada\n", nomeDaClasse);
            throw new RuntimeException(e);
        } catch (MissingResourceException e) {
            System.out.printf("Chave %s não encontrada em dao.properties\n", tipo);
            throw new RuntimeException(e);
        }

        return dao;
    }
}
