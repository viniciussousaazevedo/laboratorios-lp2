package controleAlunos;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe que realiza operações com alunos e grupos de estudo formados por estes.
 * 
 * @author Vinícius Sousa Azevedo
 */
public class ControleAlunos {
	
	/**
	 * Mapa de alunos, que associa sua matrícula (única para cada aluno) com o objeto do tipo Aluno
	 */
	private HashMap<String, Aluno> alunos;
	
	/**
	 * Mapa de objetos, que associa seu nome (único para cada grupo de estudo) com o objeto do tipo GrupoEstudo
	 */
	private HashMap<String, GrupoEstudo> gruposEstudo;

	/**
	 * Mapa que analisa o curso dos alunos, associando o nome do curso com a quantidade de grupos de estudo que tem restrição relacionada a este curso.
	 */
	private HashMap<String, Integer> contagemRestricoes;

	/**
	 * Lista dos alunos que respondem ao professor durante a aula.
	 */
	private ArrayList<Aluno> alunosQueRespondem;
	
	/**
	 * Constrói o controle de alunos, inicializando todos os atributos.
	 */
	public ControleAlunos() {
		this.alunos = new HashMap<>();
		this.gruposEstudo = new HashMap<>();
		this.contagemRestricoes = new HashMap<>();
		this.alunosQueRespondem = new ArrayList<Aluno>();
	}
	
	/**
	 * checa se o aluno está presente no mapa de alunos.
	 * 
	 * @param matricula corresponde à matricula do aluno, String de identificação única deste.
	 * @return valor booleano, que retorna true se o aluno está presente no mapa, e false se não está.
	 */
	public boolean isAlunoPresente(String matricula) {
		return this.alunos.containsKey(matricula);
	}
	
	/**
	 * checa se o grupo de estudo está presente no mapa de grupos de estudo.
	 * 
	 * @param nome corresponde ao nome do grupo, String de identificação única deste.
	 * @return valor booleano, que retorna true se o grupo de estudo está presente no mapa, e false se não está.
	 */
	public boolean isGrupoPresente(String nome) {
		return this.gruposEstudo.containsKey(nome);
	}
	
	/**
	 * cadastra um aluno com base em sua matrícula, nome e curso. Se o curso do aluno for inédito no sistema, é criada mais uma relação no atributo contagemRestricoes, envolvendo o curso inédito e o valor zero.
	 * 
	 * @param matricula corresponde à matricula do aluno, String de identificação única deste.
	 * @param nome corresponde ao nome do aluno
	 * @param curso corresponde ao curso do aluno
	 * @return valor booleano, que indica true se foi possível realizar o cadastro, e false se não foi.
	 */
	public boolean cadastraAluno(String matricula, String nome, String curso) {
		boolean retorno = false;
		curso = formataTexto(curso);
		
		if (!this.isAlunoPresente(matricula)) {
			Aluno aluno = new Aluno(matricula, nome, curso);
			this.alunos.put(matricula, aluno);
			
			if (!this.contagemRestricoes.containsKey(curso)) {
				this.contagemRestricoes.put(curso, 0);
			}
			retorno = true;
		}
		return retorno;
	}
	
	/**
	 * exibe as informações básicas do aluno com base em sua matrícula.
	 * 
	 * @param matricula corresponde à matricula do aluno, String de identificação única deste.
	 * @return o valor, em String, das informações básicas do aluno. Caso o aluno não seja encontrado, é retornada uma String vazia.
	 */
	public String exibeAluno(String matricula) {
		String retorno = "";
		if (this.isAlunoPresente(matricula)) {
			retorno =  "Aluno: " + this.alunos.get(matricula).toString();
		}
		return retorno;
	}
	
	/**
	 * cadastra um grupo de estudo com base em seu nome e restrição opcional de curso para seus participantes. Se a restrição do grupo é inédita, é criada mais uma relação no atributo contagemRestricoes, envolvendo o curso inédito e o valor um. Caso contrário, o a chave da restrição no mapa contagemRestricoes tem seu valor associado incrementado em um.
	 * 
	 * @param nome corresponde ao nome do grupo, String de identificação única deste.
	 * @param restricao corresponde à restrição opcional do grupo de estudo.
	 * @return valor booleano, que indica true se foi possível realizar o cadastro, e false se não foi.
	 */
	public boolean cadastraGrupo(String nome, String restricao) {
		boolean retorno = false;
		
		if (!this.isGrupoPresente(nome)) {
			restricao = restricao.trim();
			
			if (!restricao.equals("")) {
				restricao = formataTexto(restricao);
				if (!this.contagemRestricoes.containsKey(restricao)) {
					this.contagemRestricoes.put(restricao, 1);
				} else {
					this.contagemRestricoes.put(restricao, this.contagemRestricoes.get(restricao)+1);
				}
			}
			
			GrupoEstudo novoGrupo = new GrupoEstudo(nome, restricao);
			this.gruposEstudo.put(nome, novoGrupo);
			retorno = true;
		}
		return retorno;
	}
	
	/**
	 * Aloca um aluno já existente (com base em sua matrícula) em um grupo de estudo também já existente (com base em seu nome). Obviamente, caso haja restrição definida no grupo de estudo, apenas os alunos com o curso de acordo com a restrição podem participar.
	 * 
	 * @param matricula corresponde à matricula do aluno, String de identificação única deste.
	 * @param nomeGrupo corresponde ao nome do grupo, String de identificação única deste.
	 * @return valor booleano, que indica true caso a alocação seja efetuada com sucesso, e false caso contrário.
	 */
	public boolean alocaAlunoGrupo(String matricula, String nomeGrupo) {
		boolean retorno = false;
		Aluno aluno = this.alunos.get(matricula);
		GrupoEstudo grupo = this.gruposEstudo.get(nomeGrupo);
		
		if (
				(this.isAlunoPresente(matricula) && 
				this.isGrupoPresente(nomeGrupo)) && 
				(aluno.getCurso().equals(grupo.getRestricao()) || 
				grupo.getRestricao().equals(""))) {
			
			grupo.adicionaAluno(aluno);
			retorno = true;
		}
		return retorno;
	}
	
	/**
	 * checa se um aluno especificado por sua matrícula está alocado em um grupo especificado pelo nome.
	 * 
	 * @param nomeGrupo corresponde ao nome do grupo, String de identificação única deste.
	 * @param matriculaAluno corresponde à matricula do aluno, String de identificação única deste.
	 * @return valor booleano, que indica true caso o aluno esteja presente, ou false caso contrário (ou alguma informação passada seja inválida).
	 */
	public boolean pertinenciaGrupo(String nomeGrupo, String matriculaAluno) {
		boolean retorno = false;
		Aluno aluno = this.alunos.get(matriculaAluno);
		GrupoEstudo grupo = this.gruposEstudo.get(nomeGrupo);
		
		if (
				this.isGrupoPresente(nomeGrupo) && 
				this.isAlunoPresente(matriculaAluno) && 
				grupo.getAlunos().contains(aluno)) {
			retorno = true;
		}
		return retorno;
	}
	
	/**
	 * Cadastra um aluno já cadastrado no sistema na lista de alunos que respondem ao professor com base em sua matrícula. O mesmo aluno pode ser cadastrado na mesma lista mais de uma vez, se for o caso.
	 * 
	 * @param matricula corresponde à matricula do aluno, String de identificação única deste.
	 * @return valor booleano, que indica true caso o cadastro na lista tenha sido feito com sucesso, e false caso a matrícula seja inválida.
	 */
	public boolean cadastraAlunoQueResponde(String matricula) {
		boolean retorno = false;
		
		if (this.isAlunoPresente(matricula)) {
			this.alunosQueRespondem.add(this.alunos.get(matricula));
			retorno = true;
		}
		return retorno;
	}
	
	/**
	 * retorna a lista dos alunos que respodem ao professor.
	 * 
	 * @return lista dos alunos.
	 */
	public String listaAlunosQueRespondem() {
		String retorno = "\nAlunos:";
		int contador = 1;
		
		for (Aluno aluno : this.alunosQueRespondem) {
			retorno += "\n" + contador + ". " + aluno.toString();
			contador++;
		}
		return retorno;
	}
	
	/**
	 * exibe a contagem dos grupos que possuem restrição. Caso um aluno seja cadastrado em um curso no qual não possui grupos com restrição, este será exibido com o valor 0 (zero).
	 * 
	 * @return Contagem dos grupos que possuem restrição.
	 */
	public String contagemGruposRestricao() {
		String retorno = "";
		
		for (String curso : this.contagemRestricoes.keySet()) {
			retorno += "\nCurso: " + curso + "\n" + curso + " " + this.contagemRestricoes.get(curso) + "\n"; 
		}
		
		return retorno;
	}
	
	/**
	 * Método estático que é responsável por formatar texto do parâmetro passado, retornando o parâmetro com cada inicial em letra maiúscula.
	 * 
	 * @param valor texto a ser formatado.
	 * @return texto já formatado.
	 */
	private static String formataTexto(String valor) {
		String[] texto = valor.split(" ");
		String textoFormatado = "";
		
		for (int i = 0; i < texto.length; i++) {
			texto[i] = texto[i].substring(0,1).toUpperCase() + texto[i].substring(1);
			
			textoFormatado += texto[i];
			if (i != texto.length-1) {
				textoFormatado += " ";
			}
		}
		return textoFormatado;
	}
	
	/**
	 * retorna o atributo gruposEstudo.
	 * 
	 * @return atributo gruposEstudo.
	 */
	public HashMap<String, GrupoEstudo> getGruposEstudo() {
		return this.gruposEstudo;
	}
}
