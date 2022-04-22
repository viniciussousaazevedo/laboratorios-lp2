import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import agenda.Contato;

/**
 * Classe responsável por testar a classe Contato.
 * 
 * @author Vinícius Sousa Azevedo.
 */
public class ContatoTest {

	/**
	 * Objeto do tipo Contato que servirá como base para a execução dos testes.
	 */
	private Contato contato;
	
	/**
	 * Inicializa Contato, sendo esse método executado antes de qualquer outro método com a marcação @Test.
	 */
	@BeforeEach
	void initContato() {
		this.contato = new Contato("Matheus", "Gaudencio", "555-5551", "5555-5552", "5555-5553");
	}
	
	/**
	 * Testa o erro esperado caso o usuário tente criar um contato com nome e sobrenome vazios (ou apenas com espaços em branco).
	 */
	@Test
	void testContatoSemNomeSobrenome() {
		try {
			contato = new Contato(" ", "  ", "(Opcional)", "(Opcional)", "(Opcional)");
		} catch (IllegalArgumentException iae) {
			
		}
	}
	
	/**
	 * Testa o erro esperado caso o usuário tente criar um usuário com nome vazio.
	 */
	@Test
	void testContatoSemNome() {
		try {
			contato = new Contato("", "Gaudencio", "21010000", "", "");
		} catch (IllegalArgumentException iae) {
			
		}
	}
	
	/**
	 * Testa o erro esperado caso o usuário tente criar um usuário com sobrenome vazio.
	 */
	@Test
	void testContatoSemSobrenome() {
		try {
			contato = new Contato("Matheus", "", "21010000", "", "");
		} catch (IllegalArgumentException iae) {
			
		}
	}
	
	/**
	 * Testa o erro esperado caso o nome seja nulo.
	 */
	@Test
	void testNomeNull() {
		try {
			contato = new Contato(null, "Gaudencio", "21010000", "", "");
		} catch (NullPointerException npe) {
			
		}
	}
	
	/**
	 * Testa o erro esperado caso o sobrenome seja nulo.
	 */
	@Test
	void testAdicionalNull() {
		try {
			contato = new Contato("Matheus", "Gaudencio", "21010000", "", null);
		} catch (NullPointerException npe) {
			
		}
	}
	
	/**
	 * Testa o método setPrioritario() caso o parâmetro seja uma entráda válida.
	 */
	@Test
	void testSetPrioritario() {
		this.contato.setPrioritario("(83) 99958-8772");
	}
	
	/**
	 * Testa o método setPrioritario() caso o parâmetro seja nulo.
	 */
	@Test
	void testSetPrioritarioNull() {
		try {
			this.contato.setPrioritario(null);
		} catch (NullPointerException npe) {
			
		}
	}
	
	/**
	 * Testa o retorno esperado do método toString() em um contato normal.
	 */
	@Test
	void testToString() {
		assertEquals(
				"\n"
				+ "Matheus Gaudencio\n"
				+ "555-5551 (Prioritário)\n"
				+ "5555-5552 (Whatsapp)\n"
				+ "5555-5553 (Adicional)\n",
				this.contato.toString());
	}
	
	/**
	 * Testa o retorno esperado do método toString() em um contato favoritado.
	 */
	@Test
	void testToStringFavorito() {
		this.contato.setIsFavorito(true);
		assertEquals("\n"
				+ "❤️ Matheus Gaudencio\n"
				+ "555-5551 (Prioritário)\n"
				+ "5555-5552 (Whatsapp)\n"
				+ "5555-5553 (Adicional)\n", this.contato.toString());
	}
	
	/**
	 * Testa o retorno esperado do método toString() em um contato que tenha todos os números de telefone vazios.
	 */
	@Test
	void testToStringVazio() {
		contato = new Contato("Matheus", "Gaudencio", "", "", "");
		assertEquals(
				"\n"
				+ "Matheus Gaudencio\n"
				+ ""
				+ ""
				+ "",
				this.contato.toString());
	}
}
