package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controleAlunos.Aluno;

/**
 * Classe responsável por testar a classe Aluno.
 * 
 * @author Vinícius Sousa Azevedo
 */
class AlunoTest {

	/**
	 * Objeto do tipo Aluno, que será a base para execução dos testes.
	 */
	private Aluno aluno;
	
	/**
	 * inicializa o objeto Aluno, com o aluno Gabriel Reyes, de matricula 250 que cursa Computação.
	 */
	@BeforeEach
	void initAluno() {
		aluno = new Aluno("Gabriel Reyes", "250", "Computação");
	}
	
	/**
	 * teste referente ao método toString, comparano saídas esperadas.
	 */
	@Test
	void testToString() {
		assertEquals("Gabriel Reyes - 250 - Computação", aluno.toString());
	}

}
