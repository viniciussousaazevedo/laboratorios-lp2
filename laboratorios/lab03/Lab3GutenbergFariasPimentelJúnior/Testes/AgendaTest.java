package lab3GutenbergFariasPimentelJúnior;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AgendaTest {

	// Encerramento do programa
	@Test
	void testAgendaSaida() {
		Agenda agenda = new Agenda("s");
		assertEquals("Agenda encerrada.", this.agenda);
	}

	// Opção inválida - entrada completamente vazia/"\n"
	@Test
	void testAgendaEntradaVazia() {
		Agenda agenda = new Agenda("");
		assertEquals("Opção inválida!", this.agenda);
	}

	// Entrada inválida - opção não oferecida no menu
	@Test
	void testAgendaEntradaInvalida1() {
		Agenda agenda = new Agenda("r");
		assertEquals("Opção inválida!", this.agenda);
	}
	
	// Entrada inválida - opção não oferecida no menu. Verificação do método lower no Scanner
	@Test
	void testAgendaEntradaInvalida2() {
		Agenda agenda = new Agenda("Q");
		assertEquals("Opção inválida!", this.agenda);
	}
	
	// Entrada inválida - opção não oferecida no menu - número
	@Test
	void testAgendaEntradaInvalida3() {
		Agenda agenda = new Agenda("1");
		assertEquals("Opção inválida!", this.agenda);
	}
	
	// Verifica se a agenda inicial é vazia
	@Test
	void testAgendaContatosIniciais() {
		Agenda agenda = new Agenda("L");
		assertEquals("Lista de contatos vazia", this.agenda);
	}
	
	// Verifica se a lista de favoritos inicial é vazia
	@Test
	void testAgendaFavoritosIniciais() {
		Agenda agenda = new Agenda("f");
		assertEquals("Lista de favoritos vazia", this.agenda);
	}

	// Tenta cadastrar um contato em um índice inválido da agenda
	@Test
	void testAgendaFavoritosIndiceInvalido1() {
		Agenda agenda = new Agenda("c", 0);
		assertEquals("Índice inválido", this.agenda);
	}

	// Tenta cadastrar um contato em um índice inválido da agenda
	@Test
	void testAgendaFavoritosIndiceInvalido2() {
		Agenda agenda = new Agenda("c", 101);
		assertEquals("Índice inválido!", this.agenda);
	}
	
	// Tenta cadastrar um contato com um nome vazio
	@Test
	void testAgendaCadastroNomeVazio() {
		Agenda agenda = new Agenda("c", 1, "");
		assertEquals("Nome inválido!", this.agenda);
	}

	// Tenta cadastrar um contato sem um numero de telefone
	@Test
	void testAgendaTelefonePrimarioVazio() {
		Agenda agenda = new Agenda("c", 1, "Fulano", "Fulanosson", "");
		assertEquals("Telefone inválido!", this.agenda);
	}

	// Tenta cadastrar um contato com nome preexistente
	@Test
	void testAgendaNomeDuplicado() {
		Agenda agenda = new Agenda("c", 100, "Fulano", "Fulanossen", "1234-5678", "", false);
		Agenda agenda = new Agenda("c", 1, "Fulano", "Fulanossen", "2345-6789", "", false);
		assertEquals("Este contato já existe.", this.agenda);
	}
	
	// Tenta favoritar um contato inválido
	@Test
	void testIndiceContatoTagInvalido() {
		Agenda agenda = new Agenda("t", 0);
		assertEquals("Contato inválido!", Agenda("t", 0));
	}
	
	// Tenta favoritar um contato válido em uma posição inválida da lista de favoritos
	@Test
	void testIndiceFavoritoInvalido() {
		Agenda agenda = new Agenda();
		assertEquals();
	}
}

