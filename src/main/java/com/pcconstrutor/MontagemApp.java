package com.pcconstrutor;

import com.pcconstrutor.exception.EntidadeNaoEncontradaException;
import com.pcconstrutor.exception.ViolacaoDeConstraintException;
import com.pcconstrutor.modelo.Montagem;
import com.pcconstrutor.modelo.Peca;
import com.pcconstrutor.modelo.Usuario;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class MontagemApp {
    public static void menuMontagem() {
        boolean loop = true;
        while (loop) {
            Console.printBlock(
                    "\nMenu de Montagem\n",
                    "1. Cadastrar uma montagem",
                    "2. Alterar uma montagem",
                    "3. Remover uma montagem",
                    "4. Recuperar uma montagem",
                    "5. Listar todas as montagem",
                    "6. Listar todas as montagens de um usuário",
                    "7. Voltar"
            );

            int opcao = Console.readInt("Digite a opção desejada: ");

            Montagem montagem = null;
            switch (opcao) {
                case 1 -> {

                    String nome = Console.readLine("Digite o nome da montagem: ");

                    Usuario usuario = PrincipalApp.recuperarObjeto(
                        "Informe o número do usuário montador: ",
                        "http://localhost:8080/usuarios/{id}",
                            Usuario.class
                    );

                    Peca gabinete = recuperarPeca("o gabinete");
                    Peca fonte = recuperarPeca("a fonte");
                    Peca placaMae = recuperarPeca("a placa mãe");
                    Peca processador = recuperarPeca("o processador");
                    Peca placaDeVideo = recuperarPeca("a placa de vídeo");
                    Peca armazenamento = recuperarPeca("o armazenamento");
                    Peca memoriaRam = recuperarPeca("a memória RAM");
                    Peca coolerProcessador = recuperarPeca("o cooler do processador");
                    Peca coolerGabinete = recuperarPeca("o cooler do gabinete");

                    montagem = new Montagem(
                            nome,
                            usuario,
                            gabinete,
                            fonte,
                            placaMae,
                            processador,
                            placaDeVideo,
                            armazenamento,
                            memoriaRam,
                            coolerProcessador,
                            coolerGabinete
                    );

                    // O método exchange abaixo envia uma requisição HTTP para o endpoint fornecido.
                    // No corpo da requisição (objeto requestEntity) é enviado um objeto do tipo montagem.
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

                        ResponseEntity<Montagem> res = PrincipalApp.getTemplate().postForEntity(
                            "http://localhost:8080/montagens",
                            montagem,
                            Montagem.class
                        );
                        montagem = res.getBody();

                        System.out.println('\n' + "Montagem número " + montagem.getId() + " cadastrada com sucesso!");

                        System.out.println(montagem);
                    } catch (ViolacaoDeConstraintException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 2 -> {
                    try {
                        // Recuperando o montagem que se deseja alterar
                        montagem = PrincipalApp.recuperarObjeto(
                            "Informe o número da montagem que você deseja alterar: ",
                            "http://localhost:8080/montagens/{id}",
                            Montagem.class
                        );
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println(montagem);

                    Console.printBlock(
                            "\nO que você deseja alterar?",
                            "1. Nome",
                            "2. Usuario montador",
                            "3. Gabinete",
                            "4. Fonte",
                            "5. Placa mãe",
                            "6. Processador",
                            "7. Placa de vídeo",
                            "8. Armazenamento",
                            "9. Memória RAM",
                            "10. Cooler do processador",
                            "11. Cooler do gabinete"
                    );

                    int opcaoAlteracao = Console.readInt("\nDigite o número da opção: ");

                    boolean altera = true;
                    switch (opcaoAlteracao) {
                        case 1 -> {
                            String novoNome = Console.readLine("Novo nome: ");
                            montagem.setNome(novoNome);
                        }
                        case 2 -> {
                            Usuario novoUsuario = PrincipalApp.recuperarObjeto(
                                "Informe o número do novo usuário montador: ",
                                "http://localhost:8080/usuarios/{id}",
                                    Usuario.class
                            );
                            montagem.setUsuario(novoUsuario);
                        }
                        case 3 -> {
                            Peca novoGabinete = recuperarPeca("o novo gabinete");
                            montagem.setGabinete(novoGabinete);
                        }
                        case 4 -> {
                            Peca novaFonte = recuperarPeca("a nova fonte");
                            montagem.setFonte(novaFonte);
                        }
                        case 5 -> {
                            Peca novaPlacaMae = recuperarPeca("a nova placa mãe");
                            montagem.setPlacaMae(novaPlacaMae);
                        }
                        case 6 -> {
                            Peca novoProcessador = recuperarPeca("o novo processador");
                            montagem.setProcessador(novoProcessador);
                        }
                        case 7 -> {
                            Peca novaPlacaDeVideo = recuperarPeca("a nova placa de vídeo");
                            montagem.setPlacaDeVideo(novaPlacaDeVideo);
                        }
                        case 8 -> {
                            Peca novoArmazenamento = recuperarPeca("o novo armazenamento");
                            montagem.setArmazenamento(novoArmazenamento);
                        }
                        case 9 -> {
                            Peca novaMemoriaRam = recuperarPeca("a nova memória RAM");
                            montagem.setMemoriaRam(novaMemoriaRam);
                        }
                        case 10 -> {
                            Peca novoCoolerProcessador = recuperarPeca("o novo cooler do processador");
                            montagem.setCoolerProcessador(novoCoolerProcessador);
                        }
                        case 11 -> {
                            Peca novoCoolerGabinete = recuperarPeca("o novo cooler do gabinete");
                            montagem.setCoolerGabinete(novoCoolerGabinete);
                        }
                        default -> {
                            System.out.println("\nOpção inválida");
                            altera = false;
                        }
                    }

                    if (altera) {
                        try {
                            // Enviando uma requisição do tipo PUT para o servidor para atualizar a montagem
//                        HttpEntity<Montagem> requestEntity = new HttpEntity<>(montagem);
//                        ResponseEntity<Montagem> res = restTemplate.exchange(
//                                "http://localhost:8080/montagens",
//                                HttpMethod.PUT, requestEntity, Montagem.class);
//                        montagem = res.getBody();

                            PrincipalApp.getTemplate().put("http://localhost:8080/montagens", montagem);

                            System.out.println("\nMontagem número " + montagem.getId() + " alterada com sucesso!");

                            System.out.println(montagem);
                        } catch (ViolacaoDeConstraintException | EntidadeNaoEncontradaException e) {
                            System.out.println('\n' + e.getMessage());
                        }
                    }
                }
                case 3 -> {
                    try {
                        // Recuperando o montagem que se deseja remover
                        montagem = PrincipalApp.recuperarObjeto(
                            "Informe o número da montagem que você deseja remover: ",
                            "http://localhost:8080/montagens/{id}",
                            Montagem.class
                        );
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println(montagem);

                    String resp = Console.readLine("\nConfirma a remoção da montagem?");

                    if (resp.equals("s")) {
                        try {
                            // Enviando uma requisição do tipo DELETE para remover a montagem
//                            restTemplate.exchange(
//                                    "http://localhost:8080/montagens/{id}",
//                                    HttpMethod.DELETE, null, Montagem.class, montagem.getId());
                            PrincipalApp.getTemplate().delete("http://localhost:8080/montagens/{id}", montagem.getId());

                            System.out.println('\n' + "Montagem número " + montagem.getId() + " removida com sucesso!");
                        } catch (EntidadeNaoEncontradaException e) {
                            System.out.println('\n' + e.getMessage());
                        }
                        // Onde aparece null, poderia ter sido  especificado um  objeto do tipo  HttpEntity.
                        // Como nesta requisição http não está sendo necessário enviar nada no corpo da
                        // requisição e nem será preciso enviar nenhum header, o objeto HttpEntity não é
                        // necessário. Já o argumento Usuario.class indica o tipo do objeto que será retornado
                        // na resposta http.
                    } else {
                        System.out.println("\nMontagem não removida.");
                    }
                }
                case 4 -> {
                    try {
                        montagem = PrincipalApp.recuperarObjeto(
                            "Informe o número da montagem que você deseja recuperar: ",
                            "http://localhost:8080/montagens/{id}",
                            Montagem.class
                        );
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println(montagem);
                }
                case 5 -> {
                    // Enviando uma requisição do tipo GET para recuperar todas os montagens
                    ResponseEntity<Montagem[]> res = PrincipalApp.getTemplate().exchange(
                        "http://localhost:8080/montagens",
                        HttpMethod.GET, null,
                        Montagem[].class
                    );
                    Montagem[] montagens = res.getBody();

                    if (montagens == null) {
                        System.out.println("\nNenhuma montagem foi encontrada.");
                    } else {
                        for (Montagem umaMontagem : montagens) {
                            System.out.println(umaMontagem);
                        }
                    }

                }
                case 6 -> {
                    Console.printBlock(
                            "\nComo você deseja consultar as montagens?\n",
                            "1. Request Param",
                            "2. Path Variable"
                    );

                    int opcaoConsulta = Console.readInt("Digite a opção 1 ou 2:");

                    Montagem[] montagens = null;
                    // Enviando uma requisição do tipo GET para recuperar todas as montagens
                    // de um determinado usuário (utilizando parâmetro de requisição)
                    switch (opcaoConsulta) {
                        case 1 -> {
                            long id = Console.readInt("Informe o número do usuário: ");

                            ResponseEntity<Montagem[]> res = PrincipalApp.getTemplate().exchange(
                                "http://localhost:8080/montagens/usuario?idUsuario=" + id,
                                HttpMethod.GET, null,
                                Montagem[].class
                            );
                            montagens = res.getBody();
                        }
                        case 2 -> {
                            // Enviando uma requisição do tipo GET para recuperar todas as montagens
                            // de um determinado usuário (utilizando path variable) através do
                            // método recuperarObjeto()
                            montagens = PrincipalApp.recuperarObjeto(
                                "Informe o número do usuário: ",
                                "http://localhost:8080/montagens/usuario/{id}",
                                Montagem[].class
                            );
                        }
                        default -> System.out.println("\nOpção inválida");
                    }

                    if (montagens == null || montagens.length == 0) {
                        System.out.println("\nNenhuma montagem foi encontrada para esse usuário.");
                    } else {
                        Console.printBlock(
                            "\nComo você deseja listar as propriedades da montagem?\n",
                            "1. Exibir apenas os ids",
                            "2. Exibir apenas os nomes",
                            "3. Exibir tudo"
                        );
                        int opcaoExibicao = Console.readInt("Digite um número entre 1 e 3:");

                        switch (opcaoExibicao) {
                            case 1 -> {
                                for (Montagem umaMontagem : montagens) {
                                    System.out.println(umaMontagem.toStringId());
                                }
                            }
                            case 2 -> {
                                for (Montagem umaMontagem : montagens) {
                                    System.out.println(umaMontagem.toStringNome());
                                }
                            }
                            case 3 -> {
                                for (Montagem umaMontagem : montagens) {
                                    System.out.println(umaMontagem);
                                }
                            }
                            default -> System.out.println("\nOpção inválida!");
                        }
                    }
                }
                case 7 -> {
                    System.out.println("\nVoltando ao menu principal...");
                    loop = false;
                }
                default -> System.out.println("\nOpção inválida.");
            }
        }
    }

    private static Peca recuperarPeca(String label) {
        return PrincipalApp.recuperarObjeto(
            "Informe o número d"+ label + ": ",
            "http://localhost:8080/pecas/{id}",
            Peca.class
        );
    }
}
