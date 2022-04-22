package lab3GutenbergFariasPimentelJúnior;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CadastroTest {

	//Parametros de Cadastro.cadastraContato: int, String, String, String, String, boolean
	
	//Exibe contatos - lista vazia ao iniciar o programa
	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@BeforeEach
	void testPreCadastro() {
		Cadastro cadastro = new Cadastro.cadastraContato(1, "Fulano", "Fulanossen", "1234-5678", "8765-4321", false);
		Cadastro cadastro = new Cadastro.cadastraContato(5, "Beltrano", "Baiano", "1111-1111", "2222-2222", false);
		Cadastro cadastro = new Cadastro.cadastraContato(50, "João", "Silva", "3333-3333", "", false);
		Cadastro cadastro = new Cadastro.cadastraContato(100, "Paulo", "Pizzas", "8888-8888", "", false);
	}

	// Cadastro de novo contato em posição de agenda já ocupada. Testa também o exibeCadastro
	@Test
	void testCadastroSobrescreveContato() {
		Cadastro cadastro = new Cadastro.cadastraContato(1, "Sicrana", "Sicranasdottir", "4321-8765", "5678-1234", false);
		assertequals(Cadastro.exibeCadastro(1), this.cadastro);
	}

	// Tenta cadastrar um contato com nome vazio
	@Test
	void testCadastroNomeVazio() {
		Cadastro cadastro = new Cadastro.cadastraContato();
		assertequals();
	}

	// Tenta cadastrar um contato com telefone vazio
	@Test
	void testCadastro() {
		Cadastro cadastro = new Cadastro.cadastraContato();
		assertequals();
	}

	// Exibe contatos após cadastro do @BeforeEach
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
