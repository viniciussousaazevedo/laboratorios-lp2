package agenda;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 * Interface com menus texto para manipular uma agenda de contatos.
 * 
 * @author nazarenoandrade.
 * @author Vinícius Sousa Azevedo.
 */
public class MainAgenda {

	/**
	 * Método principal do sistema, que inicializa a interface de interação com o usuário.
	 * 
	 * @param args parâmetro necessário e característico do método principal.
	 */
	public static void main(String[] args) {
		Agenda agenda = new Agenda();

		System.out.println("Carregando agenda inicial");
		try {
			carregaAgenda("agenda_inicial.csv", agenda);
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro lendo arquivo: " + e.getMessage());
		}

		Scanner scanner = new Scanner(System.in);
		String escolha = "";
		while (true) {
			escolha = menu(scanner);
			comando(escolha, agenda, scanner);
		}

	}

	/**
	 * Menu principal de interação com o usuário, indicando as entradas válidas para interagir com o sistema.
	 * 
	 * @param scanner é o objeto que aceita as entradas do usuário.
	 * @return a entrada do usuário, formatada em letras maiúsculas.
	 */
	private static String menu(Scanner scanner) {
		System.out.print(
						"(C)adastrar Contato\n" + 
						"(L)istar Contatos\n" + 
						"(E)xibir Contato por posição\n" +
						"(EE)xibir contato por nome ou sobrenome\n" +
						"(F)avoritos\n" +
						"(A)dicionar favorito\n" +
						"(M)udar telefone prioritário de um contato existente\n" +
						"(D)eletar favorito\n" +
						"(DD)eletar contato\n" +
						"(S)air\n" + 
						"\n" + 
						"Opção> ");
		return scanner.next().toUpperCase();
	}

	/**
	 * Responável por redirecionar a entrada do usuário para o devido método que trata o que foi requisitado. Caso seja digitado uma entrada inválida, o método retorna uma mensagem de erro e volta para o menu principal do sistema.
	 * 
	 * @param opcao a entrada do usuário.
	 * @param agenda o objeto do tipo Agenda que deve ser executado de acordo com o parâmetro opção.
	 * @param scanner o objeto que aceita entradas do usuário.
	 */
	private static void comando(String opcao, Agenda agenda, Scanner scanner) {
		switch (opcao) {
		case "C":
			cadastraContato(scanner, agenda);
			break;
		case "L":
			listaContatos(agenda);
			break;
		case "E":
			exibeContatoPosicao(scanner, agenda);
			break;
		case "EE":
			exibeContatoNome(scanner, agenda);
			break;
		case "F":
			listaFavoritos(agenda);
			break;
		case "A":
			cadastraFavorito(scanner, agenda);
			break;
		case "M":
			editarPrioritario(scanner, agenda);
			break;
		case "D":
			deletarFavorito(scanner, agenda);
			break;
		case "DD":
			deletarContato(scanner, agenda);
			break;
		case "S":
			sai();
			break;
		default:
			System.out.println("OPÇÃO INVÁLIDA!\n");
		}
	}

	/**
	 * Aceita as entradas do usuário ao informar os dados de um novo contato e informa se há algum erro no cadastro ou se o cadastro foi realizado com suceso.
	 * 
	 * @param scanner o objeto que aceita entradas do usuário.
	 * @param agenda o objeto do tipo Agenda que é executado de acordo com a entradas do usuário.
	 */
	private static void cadastraContato(Scanner scanner, Agenda agenda) {
		
		System.out.print("\nPosição: ");	
		int posicao = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Nome: ");
		String nome = scanner.nextLine();
		
		System.out.print("Sobrenome: ");
		String sobrenome = scanner.nextLine();
		
		System.out.print("Prioritario: ");
		String prioritario = scanner.nextLine();
		
		System.out.print("Whatsapp: ");
		String whatsApp = scanner.nextLine();
		
		System.out.print("Adicional: ");
		String adicional = scanner.nextLine();
		
		if (!agenda.isPosicaoValidaContatos(posicao)) {
			System.out.println("POSIÇÃO INVÁLIDA\n");
		} else if (agenda.cadastraContato(posicao, nome, sobrenome, prioritario, whatsApp, adicional)) {
			System.out.println("CADASTRO REALIZADO\n");
		} else {
			System.out.println("CONTATO JA CADASTRADO\n");
		}
		
	}
	
	/**
	 * Chama o método listaContatos() do objeto agenda, listando todos os contatos cadastrados.
	 * 
	 * @param agenda o objeto do tipo Agenda que é executado de acordo com a entradas do usuário.
	 */
	private static void listaContatos(Agenda agenda) {
		
		System.out.println(agenda.listaContatos());
	}
	
	/**
	 * Aceita a entrada do usuário quanto à posição do contato a ser exibido e chama o método exibeContato(int posicao) do objeto agenda
	 * 
	 * @param scanner o objeto que aceita entradas do usuário.
	 * @param agenda o objeto do tipo Agenda que é executado de acordo com a entradas do usuário.
	 */
	private static void exibeContatoPosicao(Scanner scanner, Agenda agenda) {
		System.out.print("Contato> ");
		int posicao = scanner.nextInt();
		scanner.nextLine();
		System.out.println(agenda.exibeContato(posicao));
	}
	
	/**
	 * Aceita a etrada do usuário quanto ao nome ou sobrenome do(s) contato(s) a ser(em) exibido(s) e chama o método exibeContato(String nomeOuSobrenome) do objeto agenda.
	 * 
	 * @param scanner o objeto que aceita entradas do usuário.
	 * @param agenda o objeto do tipo Agenda que é executado de acordo com a entradas do usuário.
	 */
	private static void exibeContatoNome(Scanner scanner, Agenda agenda) {
		System.out.print("Nome ou Sobrenome> ");
		String nomeOuSobrenome = scanner.next();
		System.out.println(agenda.exibeContato(nomeOuSobrenome));
	}
	
	
	/**
	 * Aceita entradas do usuário quanto à qual contato deve ser favoritado e em qual posição da lista de favoritos e chama o método cadatraFavorito(int posicaoContato, int posicaoFavorito) do objeto agenda.
	 * 
	 * @param scanner o objeto que aceita entradas do usuário.
	 * @param agenda o objeto do tipo Agenda que é executado de acordo com a entradas do usuário.
	 */
	private static void cadastraFavorito(Scanner scanner, Agenda agenda) {
		System.out.print("Contato> ");
		int posicaoContato = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Posição> ");	
		int posicaoFavorito = scanner.nextInt();
		scanner.nextLine();
		
		if (agenda.cadastraFavorito(posicaoContato, posicaoFavorito)) {
			System.out.println("CONTATO FAVORITADO NA POSIÇÃO " + posicaoFavorito + "!\n");
		} else {
			System.out.println("POSIÇÃO INVÁLIDA!\n");
		}
	}
	
	/**
	 * Chama o método listaFavoritos() do objeto agenda, mostrando a lista de contatos favoritados.
	 * 
	 * @param agenda o objeto do tipo Agenda que é executado de acordo com a entradas do usuário.
	 */
	private static void listaFavoritos(Agenda agenda) {
		System.out.println(agenda.listaFavoritos());
	}
	
	/**
	 * Encerra a execução do sistema.
	 */
	private static void sai() {
		System.exit(0);
	}

	/**
	 * Carrega a agenda com base no arquivo agenda_inicial.csv
	 * 
	 * @param arquivoContatos o nome do arquivo ("agenda_inicial.csv").
	 * @param agenda o objeto do tipo Agenda que é executado de acordo com a entradas do usuário.
	 * @throws FileNotFoundException Caso o arquivo não exista.
	 * @throws IOException Caso não haja permissão de leitura do arquivo.
	 */
	private static void carregaAgenda(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
		LeitorDeAgenda leitor = new LeitorDeAgenda();
		
		int carregados =  leitor.carregaContatos(arquivoContatos, agenda);
		System.out.println("Carregamos " + carregados + " registros.");
	}

	/**
	 * Aceita entradas do usuário quanto à posição do contato e qual o novo número a ser inserido para enfim chamar o método editarPrioritario(int posicao, String novoPrioritario) do objeto agenda
	 * 
	 * @param scanner o objeto que aceita entradas do usuário.
	 * @param agendao objeto do tipo Agenda que é executado de acordo com a entradas do usuário.
	 */
	private static void editarPrioritario(Scanner scanner, Agenda agenda) {
		System.out.print("Posição> ");
		int posicao = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Novo número> ");
		String novoPrioritario = scanner.nextLine();
		
		agenda.editarPrioritario(posicao, novoPrioritario);
		System.out.println("NOVO NÚMERO CADASTRADO\n");
	}
	
	/**
	 * Aceita entrada do usuário quanto à posição do contato para enfim chamar o método apagarFavorito(int posicao) do objeto agenda.
	 * 
	 * @param scanner  o objeto que aceita entradas do usuário.
	 * @param agendao objeto do tipo Agenda que é executado de acordo com a entradas do usuário.
	 */
	private static void deletarFavorito(Scanner scanner, Agenda agenda) {
		System.out.print("Posição> ");
		int posicao = scanner.nextInt();
		scanner.nextLine();
		
		if (agenda.apagarFavorito(posicao)) {
			System.out.println("FAVORITO DELETADO COM SUCESSO\n");
		} else {
			System.out.println("POSIÇÃO INVÁLIDA!\n");
		}
	}

	/**
	 * Aceita entrada do usuário quanto à posição do contato para enfim chamar o método apagarContato(int posicao) do objeto agenda.
	 * 
	 * @param scanner o objeto que aceita entradas do usuário.
	 * @param agenda o objeto do tipo Agenda que é executado de acordo com a entradas do usuário.
	 */
	private static void deletarContato(Scanner scanner, Agenda agenda) {
		System.out.print("Posição> ");
		int posicao = scanner.nextInt();
		scanner.nextLine();
		
		if (agenda.apagarContato(posicao)) {
			System.out.println("CONTATO DELETADO COM SUCESSO\n");
		} else {
			System.out.println("POSIÇÃO INVÁLIDA!\n");
		}
	}
}
