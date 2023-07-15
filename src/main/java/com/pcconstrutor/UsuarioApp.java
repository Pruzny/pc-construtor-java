package com.pcconstrutor;

import com.pcconstrutor.exception.EntidadeNaoEncontradaException;
import com.pcconstrutor.exception.ViolacaoDeConstraintException;
import com.pcconstrutor.modelo.Usuario;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UsuarioApp {
    public static void menuUsuario() {
        boolean loop = true;
        while (loop) {
            Console.printBlock(
                    "\nMenu de Usuário\n",
                    "1. Cadastrar um usuário",
                    "2. Alterar um usuário",
                    "3. Remover um usuário",
                    "4. Recuperar um usuário",
                    "5. Listar todos os usuários",
                    "6. Voltar"
            );

            int opcao = Console.readInt("Digite a opção desejada: ");

            Usuario usuario = null;
            switch (opcao) {
                case 1 -> {

                    String nome = Console.readLine("\nInforme o nome: ");
                    String email = Console.readLine("Informe o email: ");
                    String senha = Console.readLine("Informe a senha: ");

                    usuario = new Usuario(
                            nome,
                            email,
                            senha
                    );

                    // O método exchange abaixo envia uma requisição HTTP para o endpoint fornecido.
                    // No corpo da requisição (objeto requestEntity) é enviado um objeto do tipo usuario.
                    // Caso seja necessário enviar algum header, deverá ser enviado como segundo argumento
                    // do construtor de HttpEntity. O método exchange retorna um objeto do tipo ResponseEntity
                    // que, por default, retorna o Status http (HttpStatus) 200 OK.

                    // Ao contrário do Postman, que exige um header do tipo Content-type valendo application/json
                    // para que seja enviado ao servidor conteúdo json incluído no corpo da requisição http,
                    // a API restTemplate já assume, por default, que será enviado json. Daí não ser necessário
                    // o envio de um header do tipo Content-type.

                    try {
                        // HttpHeaders httpHeaders = new HttpHeaders();
                        // httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                        // httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                        // HttpEntity<Usuario> requestEntity = new HttpEntity<>(umProduto, httpHeaders);

                        // Enviando uma requisição do tipo POST ao servidor para cadastrar um usuario
//                        HttpEntity<Usuario> requestEntity = new HttpEntity<>(usuario);
//                        ResponseEntity<Usuario> res = restTemplate.exchange(
//                                "http://localhost:8080/produtos",
//                                HttpMethod.POST, requestEntity, Usuario.class);
//                        usuario = res.getBody();

                        ResponseEntity<Usuario> res = PrincipalApp.getTemplate().postForEntity(
                            "http://localhost:8080/usuarios",
                            usuario,
                            Usuario.class
                        );
                        usuario = res.getBody();

                        System.out.println('\n' + "Usuário número " + usuario.getId() + " cadastrado com sucesso!");

                        System.out.println(usuario);
                    } catch (ViolacaoDeConstraintException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 2 -> {
                    try {
                        // Recuperando o usuario que se deseja alterar
                        usuario = PrincipalApp.recuperarObjeto(
                                "Informe o número do usuário que você deseja alterar: ",
                                "http://localhost:8080/usuarios/{id}", Usuario.class);
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println(usuario);

                    Console.printBlock(
                            "\nO que você deseja alterar?",
                            "1. Nome",
                            "2. Email",
                            "3. Senha"
                    );

                    int opcaoAlteracao = Console.readInt("\nDigite o número:");

                    boolean altera = true;
                    switch (opcaoAlteracao) {
                        case 1 -> {
                            String novoNome = Console.readLine("Novo nome: ");
                            usuario.setNome(novoNome);
                        }
                        case 2 -> {
                            String novoEmail = Console.readLine("Novo email: ");
                            usuario.setEmail(novoEmail);
                        }
                        case 3 -> {
                            String novaSenha = Console.readLine("Nova senha: ");
                            usuario.setSenha(novaSenha);
                        }
                        default -> {
                            System.out.println("\nOpção inválida");
                            altera = false;
                        }
                    }

                    if (altera) {
                        try {
                            // Enviando uma requisição do tipo PUT para o servidor para atualizar o usuario
//                        HttpEntity<Usuario> requestEntity = new HttpEntity<>(usuario);
//                        ResponseEntity<Usuario> res = restTemplate.exchange(
//                                "http://localhost:8080/usuarios",
//                                HttpMethod.PUT, requestEntity, Usuario.class);
//                        usuario = res.getBody();

                            PrincipalApp.getTemplate().put("http://localhost:8080/usuarios", usuario);

                            System.out.println("\nUsuário número " + usuario.getId() + " alterado com sucesso!");

                            System.out.println(usuario);
                        } catch (ViolacaoDeConstraintException | EntidadeNaoEncontradaException e) {
                            System.out.println('\n' + e.getMessage());
                        }
                    }
                }
                case 3 -> {
                    try {
                        // Recuperando o usuario que se deseja remover
                        usuario = PrincipalApp.recuperarObjeto(
                                "Informe o número do usuário que você deseja remover: ",
                                "http://localhost:8080/usuarios/{id}", Usuario.class);
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println(usuario);

                    String resp = Console.readLine("\nConfirma a remoção do usuário?");

                    if (resp.equals("s")) {
                        try {
                            // Enviando uma requisição do tipo DELETE para remover o usuario
//                            restTemplate.exchange(
//                                    "http://localhost:8080/usuarios/{id}",
//                                    HttpMethod.DELETE, null, Usuario.class, usuario.getId());
                            PrincipalApp.getTemplate().delete("http://localhost:8080/usuarios/{id}", usuario.getId());

                            System.out.println('\n' + "Usuário número " + usuario.getId() + " removido com sucesso!");
                        } catch (EntidadeNaoEncontradaException e) {
                            System.out.println('\n' + e.getMessage());
                        }
                        // Onde aparece null, poderia ter sido  especificado um  objeto do tipo  HttpEntity.
                        // Como nesta requisição http não está sendo necessário enviar nada no corpo da
                        // requisição e nem será preciso enviar nenhum header, o objeto HttpEntity não é
                        // necessário. Já o argumento Usuario.class indica o tipo do objeto que será retornado
                        // na resposta http.
                    } else {
                        System.out.println("\nUsuário não removido.");
                    }
                }
                case 4 -> {
                    try {
                        usuario = PrincipalApp.recuperarObjeto(
                                "Informe o número do usuário que você deseja recuperar: ",
                                "http://localhost:8080/usuarios/{id}", Usuario.class);
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println(usuario);
                }
                case 5 -> {
                    // Enviando uma requisição do tipo GET para recuperar todos os usuarios
                    ResponseEntity<Usuario[]> res = PrincipalApp.getTemplate().exchange(
                            "http://localhost:8080/usuarios",
                            HttpMethod.GET, null, Usuario[].class);
                    Usuario[] usuarios = res.getBody();

                    if (usuarios == null) {
                        System.out.println("\nNenhum usuário foi encontrado.");
                    } else {
                        for (Usuario umUsuario : usuarios) {
                            System.out.println(umUsuario);
                        }
                    }

                }
                case 6 -> {
                    System.out.println("\nVoltando ao menu principal...");
                    loop = false;
                }
                default -> System.out.println("\nOpção inválida.");
            }
        }
    }
}
