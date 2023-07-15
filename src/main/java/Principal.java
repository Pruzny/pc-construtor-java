
import java.util.List;

import excecao.UsuarioNaoEncontradoException;
import modelo.Montagem;
import modelo.Usuario;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import servico.UsuarioAppService;

public class Principal {
	public static void main(String[] args) {
		String nome;
		String email;
		String senha;
		Usuario usuario;

		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");
		UsuarioAppService usuarioAppService = (UsuarioAppService) fabrica.getBean("usuarioAppService");

		boolean loop = true;
		while (loop) {
			Console.printBlock(
				"MENU",
				"1. Criar usuario",
				"2. Alterar usuario",
				"3. Remover usuario",
				"4. Listar usuarios",
				"5. Listar montagens de um usuario",
				"6. Listar usuarios com montagens",
				"7. Sair"
			);

			int opcao = Console.readInt("Digite um numero entre 1 e 7: ");

			switch (opcao) {
				case 1: {
					nome = Console.readLine("Nome do usuario: ");
					email = Console.readLine("Email: ");
					senha = Console.readLine("Senha: ");

					usuario = new Usuario(nome, email, senha);

					long id = usuarioAppService.inclui(usuario);

					System.out.printf("Usuario numero %d inserido!\n", id);
					break;
				}
				case 2: {
					email = Console.readLine("Email do usuario a ser alterado: ");

					try {
						usuario = usuarioAppService.getUsuario(email);
					} catch (UsuarioNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
						break;
					}

					System.out.println(usuario);

					Console.printBlock(
						"\nO que voce deseja alterar?",
						"\t1. Nome",
						"\t2. Email",
						"\t3. Senha"
					);

					int opcaoAlteracao = Console.readInt("Digite um número de 1 a 3:");

					try {
						switch (opcaoAlteracao) {
							case 1: {
								String novoNome = Console.readLine("Novo nome: ");
								usuario.setNome(novoNome);
								usuarioAppService.altera(usuario);
								System.out.println("\nNome alterado!");
								break;
							}
							case 2: {
								String novoEmail = Console.readLine("Novo email: ");
								usuario.setEmail(novoEmail);
								usuarioAppService.altera(usuario);
								System.out.println("\nEmail alterado!");
								break;
							}
							case 3: {
								String novaSenha = Console.readLine("Nova senha: ");
								usuario.setSenha(novaSenha);
								usuarioAppService.altera(usuario);
								System.out.println("\nSenha alterada!");
								break;
							}
							default: System.out.println('\n' + "Opção inválida!");
						}
					} catch (UsuarioNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
					break;
				}
				case 3: {
					email = Console.readLine("Email do usuario a ser removido: ");

					try {
						usuario = usuarioAppService.getUsuario(email);
					} catch (UsuarioNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
						break;
					}

					System.out.println(usuario);

					String resp = Console.readLine("\nConfirma a remocao do usuario? (s/n)");

					if (resp.equals("s")) {
						try {
							usuarioAppService.exclui(usuario.getId());
							System.out.println("\nProduto removido com sucesso!");
						} catch (UsuarioNaoEncontradoException e) {
							System.out.println('\n' + e.getMessage());
						}
					} else {
						System.out.println("\nProduto não removido.");
					}
					break;
				}
				case 4: {
					List<Usuario> usuarios = usuarioAppService.getUsuarios();
					usuarios.forEach(System.out::println);
					break;
				}
				case 5: {
					email = Console.readLine("Email do usuario: ");

					try {
						usuario = usuarioAppService.getComMontagens(email);
					} catch (UsuarioNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
						break;
					}

					System.out.println(usuario);
					List<Montagem> montagens = usuario.getMontagens();
					if (montagens.isEmpty()) {
						System.out.println("Nenhuma montagem cadastrada.");
						break;
					} else {
						montagens.forEach(System.out::println);
					}
					break;
				}
				case 6: {
					List<Usuario> usuarios = usuarioAppService.getTodosComMontagens();
					for (Usuario u : usuarios) {
						System.out.println(u);
						System.out.println(u.getMontagens());
					}
					break;
				}
				case 7: {
					loop = false;
					break;
				}
				default: System.out.println("\nOpção inválida!");
			}
		}
	}
}
