package com.pcconstrutor;


import com.pcconstrutor.exception.ErrorHandler;
import com.pcconstrutor.modelo.Montagem;
import com.pcconstrutor.modelo.Peca;
import com.pcconstrutor.modelo.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PrincipalApp {

    // Nesta aplicação cliente estamos utilizando o Log4j2 (VERSÃO 2) para evitar que sejam
    // exibidas informações de DEBUG na console emitidas pelos métodos de RestTemplate do
    // framework Spring. Daí ter sido definido, no arquivo log4j2.xml, um logger Root para
    // o nível info. Os víveis de log são: trace < debug < info < warn < error < fatal

    private static Logger logger = LoggerFactory.getLogger(PrincipalApp.class);
    // private static Logger rootLogger =  LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    // Criando o objeto RestTemplate
    private static RestTemplate restTemplate = new RestTemplate();
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {

        // Designando um ErrorHandler ao objeto restTemplate
        restTemplate.setErrorHandler(new ErrorHandler());

        logger.info("Iniciando a execução da aplicação cliente.");
        // rootLogger.info("Iniciando a execução da aplicação cliente utilizando o root logger.");
        BigDecimal preco;
        LocalDate dataCadastro;
        Montagem montagem = null;

        boolean loop = true;
        while (loop) {
            Console.printBlock(
                    "\nMenu Principal",
                    "1. Menu do usuário",
                    "2. Menu da montagem",
                    "3. Menu da peça",
                    "4. Sair"
            );

//            System.out.println("\nO que você deseja fazer?");
//
//            System.out.println("7. Listar todas as montagens de um usuário");
//            System.out.println("9. Listar todas as peças");
//            System.out.println("10. Sair");

            // Métodos utilizados:
            // exchange
            // getForObject
            // postForEntity
            // put
            // delete

            int opcao = Console.readInt("\nDigite o número da opção: ");

            switch (opcao) {
                case 1 -> {
                    UsuarioApp.menuUsuario();
                }
                case 2 -> {
                    MontagemApp.menuMontagem();
                }
                case 3 -> {
                    PecaApp.menuPeca();
                }
                case 4 -> {
                    loop = false;
                }
            }
        }
    }

    static <T> T recuperarObjeto(String msg, String url, Class<T> classe) {
        int id = Console.readInt('\n' + msg);
        return restTemplate.getForObject(url, classe, id);
    }

    public static RestTemplate getTemplate() {
        return restTemplate;
    }
}
