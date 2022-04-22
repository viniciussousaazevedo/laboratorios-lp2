package lab2;

/**
 * Representação do registro de tempo online de um aluno, responsável por acompanhar o discente em uma disciplina quanto à comparação, em horas, do tempo esperado (que é o dobro da carga horária da disciplina) com o tempo real de dedicação.
 * 
 * @author Vinícius Sousa Azevedo
 */
public class RegistroTempoOnline {
	
	/**
	 * Identifica, em String, o nome da disciplina que será acompanhada.
	 */
	private String nomeDisciplina;
	/**
	 * Identifica, em um valor inteiro de horas, o tempo esperado de dedicação do aluno.
	 */
	private int TempoOnlineEsperado;
	/**
	 *Identifica, em um valor inteiro de horas, o tempo real de dedicação do aluno ao longo da disciplina. 
	 */
	private int TempoDedicado;
	

	/**
	 * Constrói o registro de tempo online do aluno a partir do nome da disciplina declarada nos parâmetros. Além disso, possui 120 horas de dedicação esperada pela disciplina (Definido por padrão). Como o registro acabou de ser criado, o tempo de dedicação atual do aluno é definido como zero horas, podendo ser incrementado.
	 * 
	 * @param nomeDisciplina é o nome da disciplina, que serve como base para identificação da disciplina a ser acompanhada.
	 */
	public RegistroTempoOnline(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
		this.TempoOnlineEsperado = 120;
		this.TempoDedicado = 0;
	}
	
	/**
	 * Constrói o registro de tempo online do aluno a partir do nome e do tempo online esperado de uma disciplina. Como o registro acabou de ser criado, o tempo de dedicação atual do aluno é definido como zero horas, podendo ser incrementado.
	 * 
	 * @param nomeDisciplina é o nome da disciplina, que serve como base para identificação da disciplina a ser acompanhada.
	 * @param TempoOnlineEsperado define, em um valor inteiro de horas, o tempo online esperado pela disciplina.
	 */
	public RegistroTempoOnline(String nomeDisciplina, int TempoOnlineEsperado) {
		this.nomeDisciplina = nomeDisciplina;
		this.TempoOnlineEsperado = TempoOnlineEsperado;
		this.TempoDedicado = 0;
	}
	

	/**
	 * Incrementa o tempo dedicado pelo aluno no decorrer da disciplina.
	 * 
	 * @param tempo indica, em um valor inteiro de horas, quanto deve ser incrementado à quantidade de tempo total dedicada pelo aluno na disciplina.
	 */
	public void adicionaTempoOnline(int tempo) {
		this.TempoDedicado += tempo;
	}
	
	/**
	 * Retorna um valor booleano que informa se o aluno conseguiu atingir a quantidade de horas de dedicação esperadas pela disciplina. É importante afirmar que o aluno não tem limite máximo de dedicação.
	 * 
	 * @return é o próprio valor booleano, retornando "true" se o aluno conseguiu atingir a meta de tempo online e "false" caso contrário.
	 */
	public boolean atingiuMetaTempoOnline() {
		boolean retorno = false;
		
		if (this.TempoDedicado >= this.TempoOnlineEsperado) {
			retorno = true;
		}
		
		return retorno;
	}

	/**
	 * Retorna o nome da disciplina que está sendo analisada quanto ao registro de tempo online do aluno.
	 * 
	 * @return é a String referente ao nome da disciplina em análise.
	 */
	public String getNomeDisciplina() {
		return this.nomeDisciplina;
	}
	
	/**
	 * Retorna a String que resume as informações gerais do registro de tempo online do aluno pela disciplina.
	 * 
	 * @return é a String que contém as informações principais da análise, sendo elas o nome da disciplina e a comparação entre o tempo dedicado pelo aluno e o tempo online esperado pela disciplina.
	 */
	@Override
	public String toString() {
		String status = this.nomeDisciplina + " " + this.TempoDedicado + "/" + this.TempoOnlineEsperado;
		return status;
	}
	
}
