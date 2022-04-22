package lab2;

import java.util.Arrays;

/**
 * Representação de uma disciplina quanto à quantidade de horas dedicadas pelo aluno e quanto à parte de notas obtidas no decorrer do aprendizado, sendo possível a análise de média tanto pelo formato aritmético quanto ponderado.
 * 
 * @author Vinícius Sousa Azevedo
 */
public class Disciplina {
	
	/**
	 * Representa, em String, o nome da disciplina que está em análise
	 */
	private String nomeDisciplina;

	/**
	 * Representa, em um valor inteiro, a quantidade de horas dedicadas pelo aluno na disciplina.
	 */
	private int qtdHoras;

	/**
	 * Representa, por meio de um array de double, as notas obtidas pelo aluno, sendo cada elemento uma nota em ordem de aplicação (isto é, o primeiro elemento é referente à primeira nota, o segundo, à segunda nota, etc.)
	 */
	private double[] listaNotas;

	/**
	 * Representa, por meio de um array de inteiros, os pesos de cada nota em ordem de aplicação (isto é, o primeiro elemento é referente ao peso da primeira nota, o segundo, à segunda nota, etc.)
	 */
	private int[] pesos;
	
	/**
	 * Constrói a disciplina com base em seu nome, na quantidade de notas e nos pesos referentes à cada nota para cálculo da média.
	 * @param nomeDisciplina é, em String, a representação do nome da disciplina.
	 * @param numNotas é a quantidade de notas que o aluno terá para cálculo da média.
	 * @param pesos é o array de valores inteiros referentes aos pesos de cada nota, em ordem de aplicação.
	 */
	public Disciplina(String nomeDisciplina, int numNotas, int[] pesos) {
		this.nomeDisciplina = nomeDisciplina;
		this.qtdHoras = 0;
		this.listaNotas = new double[numNotas];
		
		for (int i = 0; i < numNotas; i++) {
			this.listaNotas[i] = 0.0;
		}
		
		this.pesos = pesos;
	}
	
	/**
	 * Constrói a disciplina com base em seu nome e na quantidade de notas necessárias para cálculo da média final. Como não são declarados os pesos de cada nota, todas são consideradas com peso igual (ou seja, a média final será do tipo aritmética).
	 * 
	 * @param nomeDisciplina é, em String, a representação do nome da disciplina.
	 * @param numNotas é a quantidade de notas que o aluno terá para cálculo da média.
	 */
	public Disciplina(String nomeDisciplina, int numNotas) {
		this.nomeDisciplina = nomeDisciplina;
		this.qtdHoras = 0;
		this.listaNotas = new double[numNotas];

		this.pesos = new int[numNotas];
		
		for (int i = 0; i < numNotas; i++) {
			listaNotas[i] = 0.0;
			pesos[i] = 1;
		}
		
	}
	
	/**
	 * Constrói a disciplina com base apenas em seu nome. Como não são declarados detalhes como número de notas e pesos de cada nota, é considerado o padrão de 4 notas com pesos iguais (ou seja, a média final será do tipo aritmética).
	 * 
	 * @param nomeDisciplina é, em String, a representação do nome da disciplina.
	 */
	public Disciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
		this.qtdHoras = 0;
		this.listaNotas = new double[4];
		
		this.pesos = new int[4];
		
		for (int i = 0; i < 4; i++) {
			this.pesos[i] = 1;
			this.listaNotas[i] = 0.0;
		}
	}
	
	/**
	 * Incrementa a quantidade de horas dedicada pelo aluno na disciplina em questão.
	 * 
	 * @param horas é, em um valor inteiro, a quantidade de horas a ser incrementada.
	 */
	public void cadastraHoras(int horas) {
		this.qtdHoras += horas;
	}
	
	/**
	 * Cadastra uma das notas do aluno, sendo necessário informar, em double, o valor da nota e, em inteiro, o número da nota na ordem de aplicação (isto é, se é a primeira nota, segunda, terceira, etc.). Caso alguma nota seja cadastrada mais de uma vez (no mesmo número na ordem de aplicação), ela será substituída pela última informada.
	 * 
	 * @param numNota é, em um valor inteiro, o número da nota na ordem de aplicação.
	 * @param nota é, em double, o valor da nota a ser cadastrada.
	 */
	public void cadastraNota(int numNota, double nota) {
		this.listaNotas[numNota-1] = nota;
	}
	
	/**
	 * Retorna a média do aluno com as notas e pesos atuais. Caso alguma nota não seja informada, esta será considerada como zero até que seja.
	 * 
	 * @return é o valor, em double, da média obtida pelo aluno atualmente.
	 */
	public double media() {
		double media = 0;
		double somaNotas = 0;
		double somaPesos = 0;
		
		for (int i = 0; i < this.listaNotas.length; i++) {
			somaNotas += this.listaNotas[i] * this.pesos[i];
			somaPesos += this.pesos[i];
		}
		
		if (somaPesos != 0) {
			media = somaNotas / somaPesos;
		}
		
		return media;
	}
	
	/**
	 * Retorna um valor booleano referente à aprovação do aluno, que precisa ter sua média avaliada em, pelo menos, 7.0 para que seja considerado aprovado.
	 * 
	 * @return é o valor booleano a ser retornando, atribuindo "false" caso o aluno não esteja aprovado e "true" caso contrário.
	 */
	public boolean aprovado() {
		boolean retorno = false;
		
		double m = media();
		
		if (m >= 7) {
			retorno = true;
		}
		
		return retorno;
	}

	/**
	 * Retorna a String referente ao nome da disciplina que está sendo analisada.
	 * 
	 * @return é a representação em String do nome da disciplina.
	 */
	public String getNomeDisciplina() {
		return this.nomeDisciplina;
	}
	
	/**
	 * Retorna a String referente às informações principais da disciplina no tocante a quantidade de horas dedicadas e notas obtidas pelo aluno.
	 * 
	 * @return é a String que contém as principais informações, sendo elas o nome da disciplina, quantidade de horas dedicadas, média obtida pelo aluno e a lista de notas obtidas ao longo da disciplina.
	 */
	@Override
	public String toString() {
		String status = this.nomeDisciplina + " " + this.qtdHoras + " " + media() + " " + Arrays.toString(listaNotas);
		return status;
	}
	
}
