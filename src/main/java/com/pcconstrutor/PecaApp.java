package com.pcconstrutor;

import com.pcconstrutor.exception.EntidadeNaoEncontradaException;
import com.pcconstrutor.exception.ViolacaoDeConstraintException;
import com.pcconstrutor.modelo.Peca;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public class PecaApp {
    public static void menuPeca() {
        boolean loop = true;
        while (loop) {
            Console.printBlock(
                    "\nMenu de Peça\n",
                    "1. Cadastrar uma peça",
                    "2. Alterar uma peça",
                    "3. Remover uma peça",
                    "4. Recuperar uma peça",
                    "5. Listar todas as peças",
                    "6. Voltar"
            );

            int opcao = Console.readInt("Digite a opção desejada: ");

            Peca peca = null;
            switch (opcao) {
                case 1 -> {

                    String nome = Console.readLine("Digite o nome da peça: ");

                    String marca = Console.readLine("Digite a marca da peça: ");

                    BigDecimal preco = Console.readBigDecimal("Digite o preço da peça: ");

                    String tipo = Console.readLine("Digite o tipo da peça: ");

                    String descricao = Console.readLine("Digite a descrição da peça: ");

                    String imagem = Console.readLine("Digite o link da imagem: ");

                    peca = new Peca(
                        nome,
                        marca,
                        preco,
                        tipo,
                        descricao,
                        imagem
                    );

                    // O método exchange abaixo envia uma requisição HTTP para o endpoint fornecido.
                    // No corpo da requisição (objeto requestEntity) é enviado um objeto do tipo peca.
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

                        // Enviando uma requisição do tipo POST ao servidor para cadastrar um montagem
//                        HttpEntity<Usuario> requestEntity = new HttpEntity<>(montagem);
//                        ResponseEntity<Usuario> res = restTemplate.exchange(
//                                "http://localhost:8080/produtos",
//                                HttpMethod.POST, requestEntity, Usuario.class);
//                        montagem = res.getBody();

                        ResponseEntity<Peca> res = PrincipalApp.getTemplate().postForEntity(
                            "http://localhost:8080/pecas",
                            peca,
                            Peca.class
                        );
                        peca = res.getBody();

                        System.out.println('\n' + "Peça número " + peca.getId() + " cadastrada com sucesso!");

                        System.out.println(peca);
                    } catch (ViolacaoDeConstraintException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 2 -> {
                    try {
                        // Recuperando o montagem que se deseja alterar
                        peca = PrincipalApp.recuperarObjeto(
                            "Informe o número da montagem que você deseja alterar: ",
                            "http://localhost:8080/pecas/{id}",
                            Peca.class
                        );
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println(peca);

                    Console.printBlock(
                        "\nO que você deseja alterar?",
                        "1. Nome",
                        "2. Marca",
                        "3. Preço",
                        "4. Tipo",
                        "5. Descrição",
                        "6. Imagem"
                    );

                    int opcaoAlteracao = Console.readInt("\nDigite o número da opção: ");

                    boolean altera = true;
                    switch (opcaoAlteracao) {
                        case 1 -> {
                            String novoNome = Console.readLine("Novo nome: ");
                            peca.setNome(novoNome);
                        }
                        case 2 -> {
                            String novaMarca = Console.readLine("Nova marca: ");
                            peca.setMarca(novaMarca);
                        }
                        case 3 -> {
                            BigDecimal novoPreco = Console.readBigDecimal("Novo preço: ");
                            peca.setPreco(novoPreco);
                        }
                        case 4 -> {
                            String novoTipo = Console.readLine("Novo tipo: ");
                            peca.setTipo(novoTipo);
                        }
                        case 5 -> {
                            String novaDescricao = Console.readLine("Nova descrição: ");
                            peca.setDescricao(novaDescricao);
                        }
                        case 6 -> {
                            String novaImagem = Console.readLine("Novo link da imagem: ");
                            peca.setImagem(novaImagem);
                        }
                        default -> {
                            System.out.println("\nOpção inválida");
                            altera = false;
                        }
                    }

                    if (altera) {
                        try {
                        // Enviando uma requisição do tipo PUT para o servidor para atualizar a peca
//                        HttpEntity<Peca> requestEntity = new HttpEntity<>(peca);
//                        ResponseEntity<Peca> res = restTemplate.exchange(
//                                "http://localhost:8080/pecas",
//                                HttpMethod.PUT, requestEntity, Peca.class);
//                        peca = res.getBody();

                            PrincipalApp.getTemplate().put("http://localhost:8080/pecas", peca);

                            System.out.println("\nPeça número " + peca.getId() + " alterada com sucesso!");

                            System.out.println(peca);
                        } catch (ViolacaoDeConstraintException | EntidadeNaoEncontradaException e) {
                            System.out.println('\n' + e.getMessage());
                        }
                    }
                }
                case 3 -> {
                    try {
                        // Recuperando o montagem que se deseja remover
                        peca = PrincipalApp.recuperarObjeto(
                            "Informe o número da peça que você deseja remover: ",
                            "http://localhost:8080/pecas/{id}",
                            Peca.class
                        );
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println(peca);

                    String resp = Console.readLine("\nConfirma a remoção da peça?");

                    if (resp.equals("s")) {
                        try {
                            // Enviando uma requisição do tipo DELETE para remover a peça
//                            restTemplate.exchange(
//                                    "http://localhost:8080/pecas/{id}",
//                                    HttpMethod.DELETE, null, Peca.class, peca.getId());
                            PrincipalApp.getTemplate().delete("http://localhost:8080/pecas/{id}", peca.getId());

                            System.out.println('\n' + "Peça número " + peca.getId() + " removida com sucesso!");
                        } catch (EntidadeNaoEncontradaException e) {
                            System.out.println('\n' + e.getMessage());
                        }
                        // Onde aparece null, poderia ter sido  especificado um  objeto do tipo  HttpEntity.
                        // Como nesta requisição http não está sendo necessário enviar nada no corpo da
                        // requisição e nem será preciso enviar nenhum header, o objeto HttpEntity não é
                        // necessário. Já o argumento Usuario.class indica o tipo do objeto que será retornado
                        // na resposta http.
                    } else {
                        System.out.println("\nPeça não removida.");
                    }
                }
                case 4 -> {
                    try {
                        peca = PrincipalApp.recuperarObjeto(
                            "Informe o número da peça que você deseja recuperar: ",
                            "http://localhost:8080/pecas/{id}",
                            Peca.class
                        );
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println(peca);
                }
                case 5 -> {
                    // Enviando uma requisição do tipo GET para recuperar todas os pecas
                    ResponseEntity<Peca[]> res = PrincipalApp.getTemplate().exchange(
                        "http://localhost:8080/pecas",
                        HttpMethod.GET, null,
                        Peca[].class
                    );
                    Peca[] pecas = res.getBody();

                    if (pecas == null) {
                        System.out.println("\nNenhuma montagem foi encontrada.");
                    } else {
                        for (Peca umaPeca : pecas) {
                            System.out.println(umaPeca);
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
