package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controleAlunos.ControleAlunos;

/**
 * Classe usada para testar a lógica da classe CotroleAlunos.
 * 
 * @author Vinícius Sousa Azevedo
 */
class ControleAlunosTest {

	/**
	 * objeto do tipo ControleAlunos usado para implementar testes desta classe.
	 */
	private ControleAlunos controle;
	
	/**
	 * Inicializa o controle de alunos, cadastrando 4 alunos diferentes após o processo.
	 */
	@BeforeEach
	void initControleAlunos() {
		this.controle = new ControleAlunos();
		this.controle.cadastraAluno("250", "Gabriel Reyes", "Computação");
		this.controle.cadastraAluno("200","Lili Camposh", "Computação");
		this.controle.cadastraAluno("202","Angela Ziegier", "Medicina");
		this.controle.cadastraAluno("201","Torbjorn Lindholm", "Engenharia Mecânica");
	}
	
	/**
	 * Tenta cadastrar um aluno com matrícula já existente.
	 */
	@Test
	void testCadastraAlunoExistente() {
		assertFalse(this.controle.cadastraAluno("250", "Gabriel", "Administração"));
	}
	
	/**
	 * Testa a funcionalidade de exibir um aluno existente
	 */
	@Test
	void testExibeAluno() {
		assertEquals("Aluno: 250 - Gabriel Reyes - Computação", this.controle.exibeAluno("250"));
	}
	
	/**
	 * Testa a funcionalidade de exibir um aluno inexistente.
	 */
	@Test
	void testExibeAlunoInexistente() {
		assertEquals("", this.controle.exibeAluno("100"));
	}
	
	/**
	 * Testa a funcionalidade de cadastrar um grupo sem restrição definida
	 */
	@Test
	void testCadastraGrupoSemRestricao() {
		assertTrue(this.controle.cadastraGrupo("Programação OO", ""));
		assertEquals("", this.controle.getGruposEstudo().get("Programação OO").getRestricao());
	}
	
	/**
	 * Testa a funcionalidade de cadastrar um grupo com restrição definida
	 */
	@Test
	void testCadastraGrupoComRestricao() {
		assertTrue(this.controle.cadastraGrupo("Listas", "Computação"));
		assertEquals("Computação", this.controle.getGruposEstudo().get("Listas").getRestricao());
	}
	
	/**
	 * Testa a funcionalidade de cadastrar um grupo, no caso de um que ja existe no sistema.
	 */
	@Test
	void testCadastraGrupoExistente() {
		this.controle.cadastraGrupo("Listas", "Computação");
		assertFalse(this.controle.cadastraGrupo("Listas", ""));
	}

	/**
	 * Testa a funcionalidade de alocar alunos em grupos de estudo, no caso de um mesmo aluno ser alocado no mesmo grupo mais de uma vez.
	 */
	@Test
	void AlocarAlunoRepetidoGrupo() {
		this.controle.cadastraGrupo("Programação OO", "");
		
		controle.alocaAlunoGrupo("200", "Programação OO");
		controle.alocaAlunoGrupo("202", "Programação OO");
		controle.alocaAlunoGrupo("200", "Programação OO");
		
		assertTrue(this.controle.pertinenciaGrupo("Programação OO", "200"));
		assertTrue(this.controle.pertinenciaGrupo("Programação OO", "202"));
		assertEquals(2 , this.controle.getGruposEstudo().get("Programação OO").getAlunos().size());
		
		
	}

	/**
	 * Testa a funcionalidade de alocar alunos em grupo, no caso de ser um aluno inexistente.
	 */
	@Test
	void testAlocaAlunoInexistente() {
		this.controle.cadastraGrupo("Programação OO", "");
		assertFalse(this.controle.alocaAlunoGrupo("100", "Programação OO"));
	}

	/**
	 * Testa a funcionalidade de alocar alunos em grupos, no caso de ser um grupo inexistente.
	 */
	@Test
	void testAlocaGrupoInexistente() {
		assertFalse(this.controle.alocaAlunoGrupo("200", "Anatomia"));
	}

	/**
	 * Testa a funcionalidade de alocar alunos em grupos, no caso de um grupo ter restrição definida.
	 */
	@Test
	void testAlocaAlunoGrupoRestrito() {
		this.controle.cadastraGrupo("Listas", "Computação");
		
		assertTrue(this.controle.alocaAlunoGrupo("250", "Listas"));
		assertFalse(this.controle.alocaAlunoGrupo("202", "Listas"));
	}

	/**
	 * Testa a funcionalidade de pertinência de alunos em grupos
	 */
	@Test
	void testPertinencia() {
		this.controle.cadastraGrupo("Listas", "Computação");
		this.controle.alocaAlunoGrupo("250", "Listas");
		this.controle.alocaAlunoGrupo("202", "Listas");
		
		assertTrue(this.controle.pertinenciaGrupo("Listas", "250"));
		assertFalse(this.controle.pertinenciaGrupo("Listas", "202"));
	}
	
	/**
	 * Testa a funcionalidade de pertinência de alunos em grupos, no caso de se tratar de um grupo inexistete.
	 */
	@Test
	void testPertinenciaGrupoInvalido() {
		this.controle.cadastraGrupo("Listas", "Computação");
		this.controle.alocaAlunoGrupo("250", "Listas");
		
		assertFalse(this.controle.pertinenciaGrupo("List", "250"));
	}
	
	/**
	 * Testa a funcionalidade de pertinência de alunos em grupos, no caso de se tratar de um aluno inexistente.
	 */
	@Test
	void testPertinenciaAlunoInvaldo() {
		this.controle.cadastraGrupo("Listas", "Computação");
		assertFalse(this.controle.pertinenciaGrupo("Listas", "300"));
	}
	
	/**
	 * Testa a funcionalidade de cadastrar alunos já existentes na lista de alunos que respondem ao professor.
	 */
	@Test
	void testCadastraAlunoQueResponde() {
		assertTrue(this.controle.cadastraAlunoQueResponde("250"));
	}
	
	/**
	 * Testa a funcionalidade de cadastrar alunos já existentes na lista de alunos que respondem ao professor, no caso de ser um aluno inexistente.
	 */
	@Test
	void testCadastraAlunoQueRespondeInexistente() {
		assertFalse(this.controle.cadastraAlunoQueResponde("100"));
	}

	/**
	 * Testa a funcionalidade de listagem de alunos que respondem ao professor.
	 */
	@Test
	void testListaAlunosQueRespondem() {
		this.controle.cadastraAlunoQueResponde("250");
		this.controle.cadastraAlunoQueResponde("200");
		this.controle.cadastraAlunoQueResponde("202");
		
		assertEquals("\nAlunos:"
				+ "\n1. 250 - Gabriel Reyes - Computação"
				+ "\n2. 200 - Lili Camposh - Computação"
				+ "\n3. 202 - Angela Ziegier - Medicina", this.controle.listaAlunosQueRespondem());
	}
	
	/**
	 * Testa a funcionalidade de contagem de grupos com restrição no sistema.
	 */
	@Test
	void testContagemGruposRestricao() {
		this.controle.cadastraGrupo("Listas", "Computação");
		
		this.controle.cadastraGrupo("Exercicios", "Administração");
		this.controle.cadastraGrupo("Palestras", "Administração");
		
		this.controle.cadastraAluno("260", "Emanuel", "Administração");
		this.controle.cadastraAluno("270", "Emanuela", "Administração");
		this.controle.cadastraAluno("271", "Roberta", "Engenharia Civil");
		
		assertEquals("\nCurso: Engenharia Mecânica\n"
				+ "Engenharia Mecânica 0\n"
				+ "\nCurso: Computação\n"
				+ "Computação 1\n"
				+ "\nCurso: Engenharia Civil\n"
				+ "Engenharia Civil 0\n"
				+ "\nCurso: Administração\n"
				+ "Administração 2\n"
				+ "\nCurso: Medicina\n"
				+ "Medicina 0\n"
				, this.controle.contagemGruposRestricao());
	}
}
