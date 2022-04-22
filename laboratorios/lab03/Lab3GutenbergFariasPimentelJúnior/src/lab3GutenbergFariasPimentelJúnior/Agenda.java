package lab3GutenbergFariasPimentelJúnior;

import java.util.Scanner;

public class Agenda {
	/* Agenda telefônica com capacidade para 100 contatos, organizados de acordo com o número de índice.
	 * 
	 * @author Gutenberg Farias Pimentel Júnior - Matrícula 120110396
	 */

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		while (true) {
			// Imprime o menu de opções da agenda e recebe a operação a ser realizada
			System.out.println("(C)adastrar Contato");
			System.out.println("(L)istar Contatos");
			System.out.println("(E)xibir Contato");
			System.out.println("(F)avoritos");
			System.out.println("(A)dicionar Favorito");
			System.out.println("(T)ags");
			System.out.println("(S)air");
			System.out.println();
			System.out.print("Opção>");
			
			// Recebe uma operação na agenda
			String operacao = sc.next().toLowerCase().strip();
			
			//Encerra a agenda
			if (operacao.equals("s")) {
				System.out.println("Agenda encerrada.");
				sc.close();
				break;
			
			/* Cadastra um novo contato
			 * 
			 * @param indice Índice da agenda no qual o contato será cadastrado
			 * @param nome Nome do contato
			 * @param sobrenome Sobrenome do contato
			 * @param telefone Telefone do contato
			 * @param telefoneAlternativo Número de telefone alternativo para um contato
			 * @param contatoExistente Usado internamente para verificação de contatos duplicados
			 */
			} else if (operacao.equals("c")) {
				System.out.println("Índice a ser cadastrado: ");
				int indice = sc.nextInt();
				if (indice < 1 || indice > 100) {
					throw new IllegalArgumentException("Índice inválido!");
				}
				System.out.println("Nome do contato: ");
				String nome = sc.next().strip();
				if (nome.isBlank()) {
					throw new IllegalArgumentException("Nome inválido!");
				}
				System.out.println("Sobrenome do contato: ");
				String sobrenome = sc.next().strip();
				System.out.println("Telefone do contato: ");
				String telefone = sc.next().strip();
				if (telefone.isBlank()) {
					throw new IllegalArgumentException("Telefone inválido!");
				}
				System.out.println("Telefone alternativo do contato: ");
				String telefoneAlternativo = sc.next().strip();
				boolean contatoExiste;
				Cadastro cadastro = new Cadastro.cadastraContato(indice, nome, sobrenome, telefone, telefoneAlternativo, contatoExiste);
				
			// Lista todos os contatos cadastrados na agenda
			} else if (operacao.equals("l")) {
				cadastro.listaContatos();
			
			/* Exibe um contato específico cadastrado na agenda
			 * 	
			 * @param indiceAgenda Índice da agenda a ser consultado
			 */
			} else if (operacao.equals("e")) {
				System.out.print("Posição na agenda: ");
				int indiceAgenda = sc.nextInt();
				if (indiceAgenda < 1 || indiceAgenda > 100) {
					throw new IllegalArgumentException("Índice inválido!");
				} else {
					cadastro.exibeContato(indiceAgenda);
				}
		
				
			// Lista todos os favoritos cadastrados
			} else if (operacao.equals("f")) {
				Favoritos.listaFavoritos();
			
			/* Cadastra um novo contato favorito. Sobrescreve favoritos anteriores ao realizar cadastro em
			 * um mesmo índice da lista de favoritos. Suporta 10 favoritos.
			 * 
			 * @param indiceContato Posição do contato a ser favoritado na agenda
			 * @param indiceFavorito Posição do contato na lista de favoritos
			 */
			} else if (operacao.equals("a")) {
				System.out.println("Índice do contato na agenda: ");
				int indiceContato = sc.nextInt();
					if (indiceContato < 1 || indiceContato > 100) {
						throw new IllegalArgumentException("Índice inválido!");
					}
				System.out.println("Índice do contato na lista de favoritos: ");
				int indiceFavorito = sc.nextInt();
					if (indiceFavorito < 1 || indiceFavorito > 10) {
						throw new IllegalArgumentException("Índice de favorito inválido!");
					}
				Favorito favorito = new Favoritos.adicionaFavorito(indiceContato, indiceFavorito);
			
			/* Adiciona tags a contatos da agenda. Limite de 5 tags por contato.
			 * 
			 * @param contato Índice de um contato na agenda
			 * @param indiceTag Posição da tag na lista de tags de um contato
			 * @param tag Tag a ser associada ao contato
			 */
			} else if (operacao.equals("t")) {
				System.out.println("Contato(s): ");
				int contato = sc.nextInt();
				if (contato < 1 || contato > 100) {
					throw new IllegalArgumentException("Contato inválido!");
				}
				System.out.println("Índice da tag: ");
				int indiceTag = sc.nextInt();
				if (indiceTag < 1 || indiceTag > 5) {
					throw new IllegalArgumentException("Índice de tag inválido!");
				System.out.println("Tag: ");
				String tag = sc.next().strip();
				if (tag.isEmpty()) {
					throw new IllegalArgumentException("Tag não informada!");				
				Tags tag = new Tags.adicionaTag(contato, indiceTag, tag);
			
			} else {
				System.out.println("Opção inválida!");
			}
			}
			}	
		}
	}
}