import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import agenda.Agenda;
import agenda.LeitorDeAgenda;

/**
 * Classe responsável por realizar testes na classe LeitorDeAgenda.
 * 
 * @author Vinícius Sousa Azevedo.
 */
public class LeitorDeAgendaTest {
	
	/**
	 * Objeto do tipo LeitorDeAgenda que servirá como base para a execução dos testes.
	 */
	private LeitorDeAgenda leitor;
	
	/**
	 * Inicialização do objeto leitor, sendo esse método executado sempre antes de qualquer outro método com a marcação @Test.
	 */
	@BeforeEach
	void initLeitor() {
		this.leitor = new LeitorDeAgenda();
	}
	
	/**
	 * Testa o método carretaContatos() quanto ao retorno esperado da quantidade de linhas.
	 * @throws FileNotFoundException caso não seja encontrado o arquivo.
	 * @throws IOException caso não seja possível ler o arquivo.
	 */
	@Test
	void testCarregaContatos() throws FileNotFoundException, IOException {
		Agenda agenda = new Agenda();
		
		assertEquals(4, this.leitor.carregaContatos("agenda_inicial.csv", agenda));
	}
	
}
