package com.pcconstrutor;

import com.pcconstrutor.dao.UsuarioDAO;
import com.pcconstrutor.exception.UsuarioNaoEncontradoException;
import com.pcconstrutor.model.Usuario;
import com.pcconstrutor.util.FabricaDeDAOs;

import corejava.Console;

import java.util.List;

public class App {
    public static void main( String[] args ) {
        String nome;
        String email;
        String senha;
        Usuario umUsuario;

        UsuarioDAO usuarioDAO = FabricaDeDAOs.getDAO(UsuarioDAO.class);

        boolean loop = true;
        while (loop) {
            System.out.println("""
            MENU
            
            1. Criar usuário
            2. Alterar usuário
            3. Remover usuário
            4. Listar usuários
            5. Sair
            """);

            int opcao = Console.readInt("Digite um número entre 1 e 5: ");

            switch (opcao) {
                case 1 -> {
                    nome = Console.readLine("Nome do usuário: ");
                    email = Console.readLine("Email: ");
                    senha = Console.readLine("Senha: ");

                    umUsuario = new Usuario(nome, email, senha);

                    usuarioDAO.inclui(umUsuario);

                    System.out.printf("Usuário número %d inserido!\n", umUsuario.getId());
                }

                case 2 -> {
                    email = Console.readLine("Email do usuário a ser alterado: ");
                    try {
                        umUsuario = usuarioDAO.recuperaUmUsuario(email);
                    } catch (UsuarioNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.printf("""
                    \nID = %d
                    Nome = %s
                    Email = %s
                    """, umUsuario.getId(), umUsuario.getNome(), umUsuario.getEmail());

                    System.out.println("""
                    \nO que você deseja alterar?
                    1. Nome
                    2. Email
                    3. Senha
                    """);

                    int opcaoAlteracao = Console.readInt("\nDigite um número de 1 a 3:");

                    switch (opcaoAlteracao) {
                        case 1 -> {
                            String novoNome = Console.readLine("Novo nome: ");
                            umUsuario.setNome(novoNome);
                            try {
                                usuarioDAO.altera(umUsuario);
                                System.out.println("\nNome alterado!");
                            } catch (UsuarioNaoEncontradoException e) {
                                System.out.println('\n' + e.getMessage());
                            }
                        }
                        case 2 -> {
                            String novoEmail = Console.readLine("Novo email: ");
                            umUsuario.setEmail(novoEmail);
                            try {
                                usuarioDAO.altera(umUsuario);
                                System.out.println("\nEmail alterado!");
                            } catch (UsuarioNaoEncontradoException e) {
                                System.out.println('\n' + e.getMessage());
                            }
                        }
                        case 3 -> {
                            String novaSenha = Console.readLine("Nova senha: ");
                            umUsuario.setEmail(novaSenha);
                            try {
                                usuarioDAO.altera(umUsuario);
                                System.out.println("\nSenha alterada!");
                            } catch (UsuarioNaoEncontradoException e) {
                                System.out.println('\n' + e.getMessage());
                            }
                        }
                        default -> System.out.println("\nOpção inválida!");
                    }

                }
                case 3 -> {
                    email = Console.readLine("Email do usuário a ser removido: ");

                    try {
                        umUsuario = usuarioDAO.recuperaUmUsuario(email);
                    } catch (UsuarioNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.printf("""
                    \nID = %d
                    Nome = %s
                    Email = %s
                    """, umUsuario.getId(), umUsuario.getNome(), umUsuario.getEmail());

                    String resp = Console.readLine("\nConfirma a remoção do usuário? (s/n)");

                    if (resp.equals("s")) {
                        try {
                            usuarioDAO.exclui(umUsuario.getId());
                            System.out.println("\nUsuário removido!");
                        } catch (UsuarioNaoEncontradoException e) {
                            System.out.println('\n' + e.getMessage());
                        }
                    } else {
                        System.out.println("\nUsuário não removido.");
                    }

                }
                case 4 -> {
                    List<Usuario> usuarios = usuarioDAO.recuperaUsuarios();

                    usuarios.forEach(System.out::println);

                }
                case 5 -> loop = false;
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
