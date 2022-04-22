package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controleAlunos.Aluno;
import controleAlunos.GrupoEstudo;
/**
 * Classe responsável por testar a classe GrupoEstudo.
 * 
 * @author Vinícius Sousa Azevedo
 */
class GrupoEstudoTest {

	/**
	 * Objeto do tipo GrupoEstudo, que será a base para execução dos testes.
	 */
	private GrupoEstudo grupo;
	
	/**
	 * inicializa o objeto GrupoEstudo, com o grupo Amigos, com restrição para quem cursa Computação.
	 */
	@BeforeEach
	void initGrupoEstudo() {
		grupo = new GrupoEstudo("Amigos", "Computação");
	}
	
	/**
	 * testa a funcionalidade de adicionar alunos no conjunto HashSet de alunos no sistema, comparando saídas esperandas.
	 */
	@Test
	void testAdicionaAluno() {
		Aluno aluno = new Aluno("Vinícius", "120", "Computação");
		
		assertTrue(this.grupo.getAlunos().isEmpty());
		grupo.adicionaAluno(aluno);
		assertFalse(this.grupo.getAlunos().isEmpty());
	}

}
