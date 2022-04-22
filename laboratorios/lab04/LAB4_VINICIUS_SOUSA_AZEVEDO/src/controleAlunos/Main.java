package controleAlunos;

import java.util.Scanner;

/**
 * classe responsável por gerenciar comandos digitados pelo usuário para realizar operações no sistema de controle de alunos
 * 
 * @author Vinícius Sousa Azevedo
 */
public class Main {

	/**
	 * método principal, que chama o método menu() eternamente, até que o usuário queira sair do programa
	 * 
	 * @param args parâmetro nativo do método
	 */
	public static void main(String[] args) {
		ControleAlunos controle = new ControleAlunos();
		
		while (true) {
			menu(controle);
		}
	}
	
	/**
	 * menu principal do sistema, onde mostra o que é possível fazer digitando as letras destacadas em parênteses no início de cada linha
	 * 
	 * @param controle objeto do tipo ControleAlunos que irá realizar operações de acordo com as entradas do usuário
	 */
	public static void menu(ControleAlunos controle) {
		Scanner sc = new Scanner(System.in);
		System.out.print(
				"\n(C)adastrar Aluno\n"
				+ "(E)xibir Aluno\n"
				+ "(N)ovo Grupo\n"
				+ "(A)locar Aluno no Grupo e Verificar pertiência a Grupos\n"
				+ "(R)egistrar Aluno que Respondeu\n"
				+ "(I)mprimir Alunos que Responderam\n"
				+ "(O)xe, e a contagem dos grupos com restrição de curso?\n"
				+ "(S)im, quero fechar o programa!\n\n"
				+ "Opção> "
				);
		String opcao = sc.nextLine().toLowerCase();
		comando(sc, opcao, controle);
	}
	
	/**
	 * recebe o comando digitado pelo usuário e analisa qual método deve ser executado com base nisso.
	 * 
	 * @param sc objeto do tipo Scanner que lê a entrada do usuário para interação com o sistema
	 * @param opcao o valor digitado pelo usuário no método menu()
	 * @param controle objeto do tipo ControleAlunos que irá realizar operações de acordo com as entradas do usuário
	 */
	public static void comando(Scanner sc, String opcao, ControleAlunos controle) {
		switch (opcao) {
			case "c":
				cadastraAluno(sc, controle);
				break;
			case "e":
				exibeAluno(sc, controle);
				break;
			case "n":
				cadastraGrupo(sc, controle);
				break;
			case "a":
				System.out.print("\n(A)locar Aluno ou (P)ertinência a Grupo? ");
				opcao = sc.nextLine().toLowerCase();
				switch (opcao) {
					case "a":
						alocaAlunoGrupo(sc, controle);
						break;
					case "p":
						pertinenciaGrupo(sc, controle);
						break;
					default:
						comandoInvalido();
						break;
				}
				break;
			case "r":
				cadastraAlunoQueResponde(sc, controle);
				break;
			case "i":
				listaAlunosQueRespondem(controle);
				break;
			case "o":
				contagemGruposRestricao(controle);
				break;
			case "s":
				sair();
				break;
			default:
				comandoInvalido();
				break;
		}
	}
	
	/**
	 * pede os dados necessários para cadastro de aluno no sistema, chamando, por fim, o método referente do objeto controle. Caso já exista a matrícula informada no sistema ou tudo ocorra com sucesso, é retornada a informação referente.
	 * 
	 * @param sc objeto do tipo Scanner que lê a entrada do usuário para interação com o sistema
	 * @param controle objeto do tipo ControleAlunos que irá realizar operações de acordo com as entradas do usuário
	 */
	public static void cadastraAluno(Scanner sc, ControleAlunos controle) {
		System.out.print("\nMatricula: ");
		String matricula = sc.nextLine();
		
		System.out.print("Nome: ");
		String nome = sc.nextLine();
		
		System.out.print("Curso: ");
		String curso = sc.nextLine();
		
		if (controle.cadastraAluno(matricula, nome, curso)) {
			System.out.println("CADASTRO REALIZADO!");
		} else {
			System.out.println("MATRÍCULA JÁ CADASTRADA!");
		}
	}
	
	/**
	 * pede a matrícula do aluno a ser exibido, chamando, por fim, o método referente do objeto controle. Caso não exista essa matrícula no sistema, é retornada tal informação.
	 * 
	 * @param sc objeto do tipo Scanner que lê a entrada do usuário para interação com o sistema
	 * @param controle objeto do tipo ControleAlunos que irá realizar operações de acordo com as entradas do usuário
	 */
	public static void exibeAluno(Scanner sc, ControleAlunos controle) {
		System.out.print("\nMatricula: ");
		String matricula = sc.nextLine();
		
		if (!controle.isAlunoPresente(matricula)) {
			System.out.println("Aluno não cadastrado.");
		} else {
			System.out.println(controle.exibeAluno(matricula));
		}
	}
	
	/**
	 * pede os dados necessários para cadastro de grupo no sistema, chamando, por fim, o método referente do objeto controle. Caso já exista o nome informado no sistema ou o cadastro seja realizado com sucesso, é retornada a informação referente.
	 * 
	 * @param sc objeto do tipo Scanner que lê a entrada do usuário para interação com o sistema
	 * @param controle objeto do tipo ControleAlunos que irá realizar operações de acordo com as entradas do usuário
	 */
	public static void cadastraGrupo(Scanner sc, ControleAlunos controle) {
		System.out.print("\nGrupo: ");
		String nomeGrupo = sc.nextLine();
		
		System.out.print("Restrição? ");
		String restricao = sc.nextLine();
		
		if (controle.cadastraGrupo(nomeGrupo, restricao)) {
			System.out.println("CADASTRO REALIZADO!");
		} else {
			System.out.println("GRUPO JÁ CADASTRADO!");
		}
	}
	
	/**
	 * pede os dados necessários para alocar um aluno em um grupo de estudo no sistema, chamando, por fim, o método referente do objeto controle. Caso o aluno ou grupo não esteja cadastrado ou seja impossível alocar aluno devido à restrição existente no grupo ou a alocação ocorra com sucesso, o sistema retornará a informação devida.
	 * 
	 * @param sc objeto do tipo Scanner que lê a entrada do usuário para interação com o sistema
	 * @param controle objeto do tipo ControleAlunos que irá realizar operações de acordo com as entradas do usuário
	 */
	public static void alocaAlunoGrupo(Scanner sc, ControleAlunos controle) {
		System.out.print("\nMatricula: ");
		String matricula = sc.nextLine();
		
		System.out.print("Grupo: ");
		String nomeGrupo = sc.nextLine();
		
		if (!controle.isAlunoPresente(matricula)) {
			System.out.println("Aluno não cadastrado.");
		} else if (!controle.isGrupoPresente(nomeGrupo)) {
			System.out.println("Grupo não cadastrado.");
		} else if (controle.alocaAlunoGrupo(matricula, nomeGrupo)) {
			System.out.println("ALUNO ALOCADO!\n");
		} else {
			System.out.println("GRUPO COM RESTRIÇÃO DE CURSO");
		}
	}
	
	/**
	 * pede os dados necessários para checar se um aluno está presente no grupo de estudo, chamando, por fim, o método referente do objeto controle. Caso o aluno ou grupo não estejam cadastrados ou o aluno esteja ou não presente no grupo, o sistema retornará a informação devida.
	 * 
	 * @param sc objeto do tipo Scanner que lê a entrada do usuário para interação com o sistema
	 * @param controle objeto do tipo ControleAlunos que irá realizar operações de acordo com as entradas do usuário
	 */
	public static void pertinenciaGrupo(Scanner sc, ControleAlunos controle) {
		System.out.print("\nGrupo: ");
		String nomeGrupo = sc.nextLine();
		
		System.out.print("Aluno: ");
		String matriculaAluno = sc.nextLine();
		
		if (!controle.isGrupoPresente(nomeGrupo)) {
			System.out.println("GRUPO NÃO CADASTRADO.");
		} else if (controle.pertinenciaGrupo(nomeGrupo, matriculaAluno)) {
			System.out.println("ALUNO PERTENCE AO GRUPO");
		} else {
			System.out.println("ALUNO NÃO PERTENCE AO GRUPO");
		}
	}
	
	/**
	 * pede a matrícula do aluno para registrar que ele é um aluno que responde, chamando, por fim, o método referente do objeto controle. Caso o aluno seja cadastrado com sucesso ou não, o sistema retornará a informação devida.
	 * 
	 * @param sc objeto do tipo Scanner que lê a entrada do usuário para interação com o sistema
	 * @param controle objeto do tipo ControleAlunos que irá realizar operações de acordo com as entradas do usuário
	 */
	public static void cadastraAlunoQueResponde(Scanner sc, ControleAlunos controle) {
		System.out.print("\nMatricula: ");
		String matricula = sc.nextLine();
		
		if (controle.cadastraAlunoQueResponde(matricula)) {
			System.out.println("ALUNO REGISTRADO!");
		} else {
			System.out.println("Aluno não cadastrado.");
		}
	}
	
	/**
	 * mostra o retorno do método de listagem de alunos que respondem, do objeto controle.
	 * 
	 * @param controle objeto do tipo ControleAlunos que irá realizar operações de acordo com as entradas do usuário
	 */
	public static void listaAlunosQueRespondem(ControleAlunos controle) {
		System.out.println(controle.listaAlunosQueRespondem());
	}
	
	/**
	 * mostra o retorno do método de contagem de grupos com restrição, do objeto controle.
	 * 
	 * @param controle objeto do tipo ControleAlunos que irá realizar operações de acordo com as entradas do usuário
	 */
	public static void contagemGruposRestricao(ControleAlunos controle) {
		System.out.println(controle.contagemGruposRestricao());
	}
	
	/**
	 * fecha o sistema em execução
	 */
	public static void sair() {
		System.exit(0);
	}
	
	/**
	 * gera uma exceção do tipo IllegalArgumentException, já que o usuário informou um valor inválido para o sistema.
	 */
	public static void comandoInvalido() {
		throw new IllegalArgumentException("INSIRA UM VALOR VÁLIDO!\n");
	}
}