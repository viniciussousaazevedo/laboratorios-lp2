package lab2;

import static java.lang.Double.parseDouble;
import java.util.Scanner;

/**
 * É a classe que contém o método principal, adaptando o sistema de classes do COISA para que seja possível, de forma bastante simples, a aceitação de entradas do usuário a partir do teclado no próprio terminal.
 * 
 * @author Vinícius Sousa Azevedo
 */
public class CLI {
  
/**
 * É o método principal, que apenas faz a chamada d o método estatico lerComandos().
 * 
 * @param args Array de String necessário para identificação, por parte do Java, do método principal.
 */
    public static void main(String[] args) {
        
        lerComandos();

    }

	/**
	 * Inicializa um objeto aluno do tipo Aluno, contendo um laço infinito (que para apenas quando é digitado "SAIR") que aceita a entrada do usuário e separa cada termo em um elemento de um array, direcionando o programa para chamadas de métodos específicos de aluno a depender do que foi digitado. Como essa funcionalidade ainda é muito básica, ainda não há tratamento de erros e exceções.
	 * 
	 * Como o usuário deve realizar inputs:
	 * - Primeira palavra: Nome do método a ser invocado, sem espaçamento e tudo digitado com letras maiúsculas.
	 * - Segunda palavra em diante: parâmetros do método a ser invocado, na ordem da assinatura do método.
	 * 
	 */
    public static void lerComandos() {
		Aluno aluno = new Aluno();
		String[] comando;
		Scanner sc = new Scanner(System.in);

		while (true) {
			comando = sc.nextLine().split(" ");
			switch (comando[0]) {
				case "REGISTROTEMPOONLINE":
					if (comando.length == 2) {
						aluno.registroTempoOnline(comando[1]);
					} else if (comando.length == 3) {
						aluno.registroTempoOnline(comando[1], Integer.parseInt(comando[2]));
					}
					break;
				case "ADICIONATEMPOONLINE":
					aluno.adicionaTempoOnline(comando[1], Integer.parseInt(comando[2]));
					break;
				case "ATINGIUMETAONLINE":
					System.out.println(aluno.atingiuMetaOnline(comando[1]));
					break;
				case "REGISTROONLINETOSTRING":
					System.out.println(aluno.registroOnlineToString(comando[1]));
					break;
				case "CADASTRADISCIPLINA":
					aluno.cadastraDisciplina(comando[1]);
					break;
				case "CADASTRAHORAS":
					aluno.cadastraHoras(comando[1], Integer.parseInt(comando[2]));
					break;
				case "CADASTRANOTA":
					aluno.cadastraNota(comando[1], Integer.parseInt(comando[2]), parseDouble(comando[3]));
					break;
				case "APROVADO":
					System.out.println(aluno.aprovado(comando[1]));
					break;
				case "DISCIPLINATOSTRING":
					System.out.println(aluno.disciplinaToString(comando[1]));
					break;
				case "CADASTRAFINANCAS":
					aluno.cadastraFinancas(Integer.parseInt(comando[1]));
					break;
				case "AUMENTARECEITA":
					aluno.aumentaReceita(Integer.parseInt(comando[1]), Integer.parseInt(comando[2]));
					break;
				case "PAGADESPESA":
					aluno.pagaDespesa(Integer.parseInt(comando[1]));
					break;
				case "EXIBEFONTES":
					System.out.println(aluno.exibeFontes());
					break;
				case "FINANCASTOSTRING":
					System.out.println(aluno.financasToString());
					break;
				case "DEFINESAUDEMENTAL":
					aluno.defineSaudeMental(comando[1]);
					break;
				case "DEFINESAUDEFISICA":
					aluno.defineSaudeFisica(comando[1]);
					break;
				case "GETSTATUSGERAL":
					System.out.println(aluno.getStatusGeral());
					break;
				case "SAIR":
					System.exit(0);
					break;
				default:
					System.out.println("Insira um valor válido.");
			}
		}
	}

}
