package controleAlunos;

/**
 * Representação de um Aluno, havendo sua representação quanto a matrícula (sendo esta única para cada aluno), nome e curso atual.
 * 
 * @author Vinícius Sousa Azevedo
 */
public class Aluno {

	/**
	 * representação da matrícula, em String.
	 */
	private String matricula;

	/**
	 * representação do nome do aluno, em String.
	 */
	private String nome;

	/**
	 * representação do curso do aluno, em String.
	 */
	private String curso;
	
	/**
	 * Constrói o aluno, sendo passado os valores que devem ser atribuídos aos seus atributos.
	 * 
	 * @param matricula a matrícula a ser definida para o aluno.
	 * @param nome seu nome.
	 * @param curso seu curso atual.
	 */
	public Aluno(String matricula, String nome, String curso) {
		this.matricula = matricula;
		this.nome = nome;
		this.curso = curso;
	}
	
	/**
	 * retorna a matrícula do aluno.
	 * 
	 * @return a matrícula, em String.
	 */
	public String getMatricula() {
		return this.matricula;
	}

	/**
	 * retorna o curso do aluno.
	 * 
	 * @return o curso, em String.
	 */
	public String getCurso() {
		return this.curso;
	}
	
	/**
	 * implementação do hashCode do aluno, já que a matrícula é um atributo único para cada objeto do tipo Aluno.
	 * 
	 * @return o valor inteiro referente ao HashCode da classe.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	/**
	 * implementação do equals do aluno, já que a matrícula é um atributo único para cada objeto do tipo Aluno.
	 * 
	 * @param obj é o objeto a ser comparado, do tipo Object.
	 * @return o valor, em booleano, que informa se o objeto obj é igual ou não à classe Aluno, tendo como base, a matrícula.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	/**
	 * implementação do toString do aluno, sendo passado seus atributos previamente definidos.
	 * 
	 * @return a String que contem as informações principais do aluno.
	 */
	@Override
	public String toString() {
		
		return this.matricula + " - " + this.nome + " - " + this.curso;
	}
	
}
