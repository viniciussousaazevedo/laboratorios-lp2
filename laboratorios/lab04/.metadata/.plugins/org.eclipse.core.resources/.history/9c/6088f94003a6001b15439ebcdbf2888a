package controleAlunos;

import java.util.HashSet;

/**
 * Classe que representa um grupo de estudos composto por alunos, tendo um nome e uma restrição de curso (opcional).
 * 
 * @author Vinícius Sousa Azevedo
 */
public class GrupoEstudo {

	/**
	 * nome do grupo, em String.
	 */
	private String nome;

	/**
	 * restrição opcional do grupo, em String. Caso não haja restrição, o valor deste atributo é uma String vazia.
	 */
	private String restricao;

	/**
	 * a lista de alunos no formato de um HashSet do objeto Aluno.
	 */
	private HashSet<Aluno> alunos;
	
	/**
	 * Constrói o grupo de estudos com base em seu nome e restrição opcional, além de inicializar o conjunto de alunos.
	 * @param nome o nome do grupo, em String.
	 * @param restricao a restrição opcional do grupo, em String.
	 */
	public GrupoEstudo(String nome, String restricao) {
		this.nome = nome;
		this.restricao = restricao;
		alunos = new HashSet<Aluno>();
	}
	
	/**
	 * retorna o nome do grupo, em String.
	 * @return o nome do grupo, em String.
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * retorna a restrição opcional do grupo, em String.
	 * 
	 * @return a restrição opcional do grupo, em String.
	 */
	public String getRestricao() {
		return this.restricao;
	}
	
	/**
	 * retorna o conjunto de alunos que compõem o grupo, no formato HashSet
	 * @return o cojunto de alunos, no formato HashSet.
	 */
	public HashSet<Aluno> getAlunos() {
		return this.alunos;
	}
	
	/**
	 * adiciona um aluno especificado na lista de conjunto de alunos do grupo de estudo.
	 * @param aluno o aluno a ser adicionado no conjunto de alunos.
	 */
	public void adicionaAluno(Aluno aluno) {
		this.alunos.add(aluno);
	}
	
	/**
	 * implementação do valor do hashCode da classe, que possui como valor único de cada objeto o atributo nome.
	 * 
	 * @return o valor inteiro referente ao hashCode do objeto.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * implementação do valor do equals da classe, que possui valor único de cada objeto o atributo nome.
	 * 
	 * @param obj o objeto do tipo Object a ser comparado com a classe.
	 * @return o valor booleano referente a se o parâmetro obj é igual ou não à classe em questão.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrupoEstudo other = (GrupoEstudo) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
