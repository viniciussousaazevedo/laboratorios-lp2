import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotEquals;


import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import agenda.Agenda;

/**
 * Classe responsável por realizar testes na classe Agenda.
 * 
 * @author Vinícius Sousa Azevedo.
 */
public class AgendaTest {

	/**
	 * Objeto do tipo Agenda que servirá como base para a realização dos testes.
	 */
	private Agenda agenda;
	
	/**
	 * Inicializa a agenda, sendo esse método executado sempre antes de qualquer outro método com a marcação @Test.
	 */
	@BeforeEach
	void initAgenda() {
		this.agenda = new Agenda();
	}
	
	/**
	 * Testa o cadastro normal de um contato, com todos os campos preenchidos e a locação sendo em uma posição vazia.
	 */
	@Test
	void testCadastraContato() {
		assertTrue(this.agenda.cadastraContato(
				1,
				"Matheus",
				"Gaudencio",
				"(83) 99999-0000",
				"(83) 99999-0001",
				"(83) 99999-0002"));
	}
	
	/**
	 * Testa o cadastro de um contato em uma posição na qual já tinha um contato cadastrado.
	 */
	@Test
	void testCadastraContatoPosicaoExistente() {
		this.agenda.cadastraContato(
				1,
				"Matheus",
				"Gaudencio",
				"(83) 99999-0000",
				"(83) 99999-0001",
				"(83) 99999-0002");
		assertTrue(this.agenda.cadastraContato(
				1,
				"Pedro",
				"Silva",
				"(84) 98888-1111",
				"(84) 98888-1112",
				"(84) 98888-1113"));
	}
	
	/**
	 * Testa se o cadastro é interrompido caso o usuário tente cadastrar um contato com um nome já presente na lista de contatos.
	 */
	@Test
	void testCadastraContatoRepetido() {
		this.agenda.cadastraContato(
				1,
				"Matheus",
				"Gaudencio",
				"(83) 99999-0000",
				"(83) 99999-0001",
				"(83) 99999-0002");
		
		assertFalse(this.agenda.cadastraContato(
				3,
				"Matheus",
				"Gaudencio",
				"(83) 1234-5678",
				"(83) 2345-6789",
				"(83) 3456-7890"));
	}

	/**
	 * Testa o cadastro de um contato na posição limite (100).
	 */
	@Test
	void testCadastraContatoPosicaoLimite() {
		assertTrue(this.agenda.cadastraContato(
				100,
				"Matheus",
				"Gaudencio",
				"(83) 99999-0000",
				"(83) 99999-0001",
				"(83) 99999-0002"));
	}

	/**
	 * Testa o cadastro de um contato acima da posição limite (101 ou mais), retornando um valor falso.
	 */
	@Test
	void testCadastraContatoAcimaPosicaoLimite() {
		assertFalse(this.agenda.cadastraContato(
				101,
				"Matheus",
				"Gaudencio",
				"(83) 99999-0000",
				"(83) 99999-0001",
				"(83) 99999-0002"));
	}

	/**
	 * Testa o cadastro de um contato na primeira posição (1).
	 */
	@Test
	void testCadastraContatoPrimeiraPosicao() {
		assertTrue(this.agenda.cadastraContato(
			1,
			"Matheus",
			"Gaudencio",
			"(83) 99999-0000",
			"(83) 99999-0001",
			"(83) 99999-0002"));
	}

	/**
	 * Testa o cadastro de um contato abaixo da primeira posição (1), retornando um valor falso.
	 */
	@Test
	void testCadastraContatoAbaixoPosicaoLimite() {
		assertFalse(this.agenda.cadastraContato(
				0,
				"Matheus",
				"Gaudencio",
				"(83) 99999-0000",
				"(83) 99999-0001",
				"(83) 99999-0002"));
	}

	/**
	 * Testa o cadastro de um contato com o telefone adicional vazio.
	 */
	@Test
	void testCadastraContatoAdicionalVazio() {
		assertTrue(this.agenda.cadastraContato(
				1,
				"Matheus",
				"Gaudencio",
				"(83) 99999-0000",
				"(83) 99999-0001",
				""));
	}

	/**
	 * Testa a exibição de um contato com todos os campos devidamente preenchidos.
	 */
	@Test
	void testExibeContatoCompleto() {
		this.agenda.cadastraContato(1,
				"Matheus",
				"Gaudencio",
				"(83) 99999-0000",
				"(83) 99999-0001",
				"(83) 99999-0002");
		assertEquals("\n"
				+ "Matheus Gaudencio\n"
				+ "(83) 99999-0000 (Prioritário)\n"
				+ "(83) 99999-0001 (Whatsapp)\n"
				+ "(83) 99999-0002 (Adicional)\n",
				this.agenda.exibeContato(1));
	}

	/**
	 * Testa a exibição de um contato com um dos telefones vazios (no caso, telefone adicional vazio).
	 */
	@Test
	void testExibeContatoSemUmTelefone() {
		this.agenda.cadastraContato(1,
				"Matheus",
				"Gaudencio",
				"(83) 99999-0000",
				"(83) 99999-0001",
				"");
		assertEquals("\n"
				+ "Matheus Gaudencio\n"
				+ "(83) 99999-0000 (Prioritário)\n"
				+ "(83) 99999-0001 (Whatsapp)\n"
				+ "", this.agenda.exibeContato(1));
	}

	/**
	 * Testa a mensagem de erro que deve aparecer caso o usuário tente exibir um contato que não existe.
	 */
	@Test
	void testExibeContatoPosicaoNula() {
		assertEquals("POSIÇÃO INVÁLIDA!\n", this.agenda.exibeContato(100));
	}
	
	/**
	 * Testa a mensagem de erro que deve aparecer caso o usuário tente exibir um contato em uma posição inválida (menor que a primeira posição disponível).
	 */
	@Test
	void testExibeContatoAbaixoPosicaoLimite() {
		assertEquals("POSIÇÃO INVÁLIDA!\n", this.agenda.exibeContato(0));
	}

	/**
	 * Testa a mensagem de erro que deve aparecer caso o usuário tente exibir um contato em uma posição inválida (maior que a última posição disponível).
	 */
	@Test
	void testExibeContatoAcimaPosicaoLimite() {
		assertEquals("POSIÇÃO INVÁLIDA!\n", this.agenda.exibeContato(101));
	}

	/**
	 * Testa a exibição de um contato favoritado com todos os campos devidamente preenchidos.
	 */
	@Test
	void testExibeContatoFavorito() {
		this.agenda.cadastraContato(1,
				"Matheus",
				"Gaudencio",
				"(83) 99999-0000",
				"(83) 99999-0001",
				"(83) 99999-0002");
		this.agenda.cadastraFavorito(1, 1);
		
		assertEquals(
				"\n"
				+ "❤️ Matheus Gaudencio\n"
				+ "(83) 99999-0000 (Prioritário)\n"
				+ "(83) 99999-0001 (Whatsapp)\n"
				+ "(83) 99999-0002 (Adicional)\n", this.agenda.exibeContato(1));
	}
	
	/**
	 * Testa a funcionalidade de exibição de contato sendo a identificação pelo nome.
	 */
	@Test
	void testExibeContatoPorNome() {
		this.agenda.cadastraContato(1,
				"Matheus",
				"Gaudencio",
				"(83) 99999-0000",
				"(83) 99999-0001",
				"(83) 99999-0002");
		this.agenda.cadastraContato(5,
				"Matheus",
				"Vieira",
				"123",
				"234",
				"345");
		this.agenda.cadastraContato(10,
				"Vinicius",
				"Vieira",
				"1",
				"2",
				"3");
		
		assertEquals("\n"
				+ "Matheus Gaudencio\n"
				+ "(83) 99999-0000 (Prioritário)\n"
				+ "(83) 99999-0001 (Whatsapp)\n"
				+ "(83) 99999-0002 (Adicional)\n"
				+ "\n"
				+ "Matheus Vieira\n"
				+ "123 (Prioritário)\n"
				+ "234 (Whatsapp)\n"
				+ "345 (Adicional)\n", this.agenda.exibeContato("Matheus"));
	}
	
	/**
	 * Testa a funcionalidade de exibição de contato sendo a identificação pelo sobrenome.
	 */
	@Test
	void testExibeContatoPorSobrenome() {
		this.agenda.cadastraContato(1,
				"Matheus",
				"Gaudencio",
				"(83) 99999-0000",
				"(83) 99999-0001",
				"(83) 99999-0002");
		this.agenda.cadastraContato(5,
				"Gabriel",
				"Gaudencio",
				"123",
				"234",
				"345");
		this.agenda.cadastraContato(10,
				"Matheus",
				"Vieira",
				"1",
				"2",
				"3");
		
		assertEquals("\n"
				+ "Matheus Gaudencio\n"
				+ "(83) 99999-0000 (Prioritário)\n"
				+ "(83) 99999-0001 (Whatsapp)\n"
				+ "(83) 99999-0002 (Adicional)\n"
				+ "\n"
				+ "Gabriel Gaudencio\n"
				+ "123 (Prioritário)\n"
				+ "234 (Whatsapp)\n"
				+ "345 (Adicional)\n", this.agenda.exibeContato("Gaudencio"));
		
	}

	/**
	 * Testa a mensagem de erro que deve aparecer caso o usuário insira um valor vazio na busca por contato pelo nome ou sobrenome.
	 */
	@Test
	void testExibeContatoPorNomeVazio() {
		this.agenda.cadastraContato(1, "Vinicius", "Azevedo", "1111", "2222", "3333");
		
		assertEquals("NÃO HÁ CONTATOS COM ESTE NOME\n", this.agenda.exibeContato(""));
	}
	
	/**
	 * Test a funcionalidade de cadastro de favorito com todos os campos devidamente preenchidos.
	 */
	@Test
	void testCadastraFavorito() {
		this.agenda.cadastraContato(1, "Vinicius", "Azevedo", "1111", "2222", "3333");
		assertTrue(this.agenda.cadastraFavorito(1, 4));
	}
	
	/**
	 * Testa o caso em que o usuário tenta favoritar o mesmo contato em posições diferentes da lista de favoritos.
	 */
	@Test
	void testCadastroFavoritoRepetido() {
		this.agenda.cadastraContato(
				1,
				"Matheus",
				"Gaudencio",
				"(83) 99999-0000",
				"(83) 99999-0001",
				"(83) 99999-0002");
		this.agenda.cadastraFavorito(1, 1);
		
		assertFalse(this.agenda.cadastraFavorito(1, 4));
	}

	/**
	 * Testa o caso de cadastro de favorito onde há uma substituição do contato pré-existente pelo novo sendo cadastrado.
	 */
	@Test
	void testCadastraFavoritoSubstituido() {
		this.agenda.cadastraContato(
				1,
				"Matheus",
				"Gaudencio",
				"(83) 99999-0000",
				"(83) 99999-0001",
				"(83) 99999-0002");
		this.agenda.cadastraContato(
				2,
				"Vinícius",
				"Azevedo",
				"123",
				"234",
				"");
		
		this.agenda.cadastraFavorito(1, 1);
		assertTrue(this.agenda.cadastraFavorito(2, 1));
		
		
	}

	/**
	 * Testa o caso em que o usuário tenta cadastrar um contato abaixo da primeira posição disponível (1).
	 */
	@Test
	void testCadastraFavoritoAbaixoPosicaoLimite() {
		this.agenda.cadastraContato(1, "Vinicius", "Azevedo", "1111", "2222", "3333");
		assertFalse(this.agenda.cadastraFavorito(1, 0));
	}
	
	/**
	 * Testa o caso em que o usuário tenta cadastrar um contato acima da posição limite (10).
	 */
	@Test
	void testCadastraFavoritoAcimaPosicaoLimite() {
		this.agenda.cadastraContato(1, "Vinicius", "Azevedo", "1111", "2222", "3333");
		assertFalse(this.agenda.cadastraFavorito(1, 11));
	}
	
	/**
	 * Testa a funcinalidade de listagem de contatos.
	 */
	@Test
	void testListaContatos() {
		this.agenda.cadastraContato(1, "Vinicius", "Azevedo", "1111", "2222", "3333");
		this.agenda.cadastraContato(98, "Gaudencio", "Do Overwatch", "4444", "5555", "6666");
		
		assertEquals("\n"
				+ "1 - Vinicius Azevedo\n"
				+ "98 - Gaudencio Do Overwatch\n", this.agenda.listaContatos());
	}
	
	/**
	 * Testa a lsitagem de contatos em caso de não haver nenhum contato cadastrado.
	 */
	@Test
	void testListaContatosVazio() {
		assertEquals("\n", this.agenda.listaContatos());
	}
	
	/**
	 * Testa a funcionalidade de listagem de contatos favoritados.
	 */
	@Test
	void testListaFavoritos() {
		this.agenda.cadastraContato(1, "Vinicius", "Azevedo", "1111", "2222", "3333");
		this.agenda.cadastraContato(98, "Gaudencio", "Do Overwatch", "4444", "5555", "6666");
		this.agenda.cadastraContato(56, "Teste", "Em listas", "", "", "");
		
		this.agenda.cadastraFavorito(1, 1);
		this.agenda.cadastraFavorito(98, 8);
		
		assertEquals("\n"
				+ "1 - Vinicius Azevedo\n"
				+ "8 - Gaudencio Do Overwatch\n", this.agenda.listaFavoritos());
	}
	
	/**
	 * Testa a listagem de contatos favoritos no caso de não haver nenhum contato cadastrado.
	 */
	@Test
	void testListaFavoritosVazio() {
		assertEquals("\n", this.agenda.listaFavoritos());
	}
	
	/**
	 * Testa a funcionalidade de editar o telefone prioritário do contato.
	 */
	@Test
	void testEditarPrioritario() {
		this.agenda.cadastraContato(1, "Vinicius", "Azevedo", "1111", "2222", "3333");
		this.agenda.editarPrioritario(1, "0000");
	}

	/**
	 * Testa a funcionalidade de editar o telefone prioritário do contato em caso do novo telefone ser um valor vazio.
	 */
	@Test
	void testEditarPrioritarioVazio() {
		this.agenda.cadastraContato(1, "Vinicius", "Azevedo", "1111", "2222", "3333");
		this.agenda.editarPrioritario(1, "");
	}

	/**
	 * Testa o impedimento do sistema de registrar um novo telefone prioritário do tipo nulo.
	 */
	@Test
	void testEditarPrioritarioNull() {
		this.agenda.cadastraContato(1, "Vinicius", "Azevedo", "1111", "2222", "3333");
		
		try {
			this.agenda.editarPrioritario(1, null);
		} catch (NullPointerException npe) {
			
		}
		
	}
	
	/**
	 * Testa a funcionalidade de apagar um contato.
	 */
	@Test
	void testApagarContato() {
		this.agenda.cadastraContato(1, "Vinicius", "Azevedo", "1111", "2222", "3333");
		this.agenda.cadastraContato(98, "Gaudencio", "Do Overwatch", "4444", "5555", "6666");
		
		assertTrue(this.agenda.apagarContato(1));
		assertEquals("\n98 - Gaudencio Do Overwatch\n", this.agenda.listaContatos());
	}
	
	/**
	 * Testa a funcionalidade de apagar um contato que não existe.
	 */
	@Test
	void testApagarContatoPosicaoNull() {
		this.agenda.cadastraContato(1, "Vinicius", "Azevedo", "1111", "2222", "3333");
		
		assertFalse(this.agenda.apagarContato(10));
	}
	
	/**
	 * Testa a funcionalidade de apagar um contato numa posição inválida (abaixo da posição limite).
	 */
	@Test
	void testApagarContatoAbaixoPosicaoLimite() {
		this.agenda.cadastraContato(1, "Vinicius", "Azevedo", "1111", "2222", "3333");
		
		assertFalse(this.agenda.apagarContato(0));
	}
	
	/**
	 * Testa a funcionalidade de apagar um contato numa posição inválida (acima da posição limite).
	 */
	@Test
	void testApagarContatoAcimaPosicaoLimite() {
		this.agenda.cadastraContato(1, "Vinicius", "Azevedo", "1111", "2222", "3333");
		
		assertFalse(this.agenda.apagarContato(101));
	}
	
	/**
	 * Testa a funcionalidade de apagar um contato favoritado.
	 */
	@Test
	void testApagarFavorito() {
		this.agenda.cadastraContato(1, "Vinicius", "Azevedo", "1111", "2222", "3333");
		this.agenda.cadastraContato(98, "Gaudencio", "Do Overwatch", "4444", "5555", "6666");
		
		this.agenda.cadastraFavorito(1, 1);
		this.agenda.cadastraFavorito(98, 8);
		
		assertTrue(this.agenda.apagarFavorito(8));
		assertEquals("\n1 - Vinicius Azevedo\n", this.agenda.listaFavoritos());
		
	}
	
	/**
	 * Testa a funcionalidade de apagar um contato favoritado que não existe.
	 */
	@Test
	void testApagarFavoritoPosicaoNull() {
		this.agenda.cadastraContato(1, "Vinicius", "Azevedo", "1111", "2222", "3333");
		this.agenda.cadastraFavorito(1, 1);
		
		assertFalse(this.agenda.apagarFavorito(10));
	}
	
	/**
	 * Testa a funcionalidade de apagar um contato favoritado numa posição inválida (abaixo da posição limite).
	 */
	@Test
	void testApagarFavoritoAbaixoPosicaoLimite() {
		this.agenda.cadastraContato(1, "Vinicius", "Azevedo", "1111", "2222", "3333");
		this.agenda.cadastraFavorito(1, 1);
		
		assertFalse(this.agenda.apagarFavorito(0));
	}
	
	/**
	 * Testa a funcionalidade de apagar um contato favoritado numa posição inválida (acima da posição limite).
	 */
	@Test
	void testApagarFavoritoAcimaPosicaoLimite() {
		this.agenda.cadastraContato(1, "Vinicius", "Azevedo", "1111", "2222", "3333");
		this.agenda.cadastraFavorito(1, 1);
		
		assertFalse(this.agenda.apagarFavorito(11));
	}

}