package lab2;

/**
 * Representa o registro financeiro do aluno no decorrer de seu curso, analisando receitas e despesas relacioadas.
 * 
 * @author Vinícius Sousa Azevedo
 */
public class RegistroFinancas {

	/**
	 * Representa, em um valor inteiro de centavos, a receita total do aluno.
	 */
	private int receitaTotal;

	/**
	 * Representa, em um valor inteiro de centavos, a receita atual do aluno.
	 */
	private int receitaAtual;

	/**
	 * Representa, em um valor inteiro de centavos, a despesa total do aluno.
	 */
	private int despesas;

	/**
	 * Representa, em um array de valores inteiros de centavos, as receitas referentes a 4 tipos de fontes. O primeiro elemento é a receita vinda da família, o segundo é a receita vinda de projetos institucionais com bolsa, a terceira, auxílio institucional e a quarta, doações externas.
	 */
	int[] listaReceitas = {0, 0, 0, 0};

	/**
	 * Representa, em um array de String, os últimos 5 detalhes que explicam melhor o incremento de despesas.
	 */
	String[] historicoDespesas;
	
	/**
	 * Consrói o registro financeiro com base na receita inicial do aluno, que se encaixa obrigatoriamente na receita de primeiro tipo, isto é, familiar. Além disso, as despesas são consideradas como zero, podendo ser incrementadas.
	 * 
	 * @param receitaInicialDoTipo1 representa, em um valor inteiro de centavos, a receita inicial do aluno, de origem familiar.
	 */
	public RegistroFinancas(int receitaInicialDoTipo1) {
		this.receitaTotal = receitaInicialDoTipo1;
		this.receitaAtual = receitaInicialDoTipo1;
		this.despesas = 0;
		this.listaReceitas[0] = receitaInicialDoTipo1;
		this.historicoDespesas = new String[5];

		for (int i = 0; i < this.historicoDespesas.length; i++) {
			this.historicoDespesas[i] = "";
		}
		
	}
	
	/**
	 * Aumenta o valor total e atual da receita do aluno, além de incrementar o valor relacionado à receita específica do tipo de fonte declarada.
	 * 
	 * @param valorCentavos representa, em um valor inteiro de centavos, a quantidade financeira a ser incrementada
	 * @param tipoFonte representa, em um valor inteiro de 1 a 4, qual o tipo de origem dessa receita.
	 */
	public void aumentaReceita(int valorCentavos, int tipoFonte) {
		this.listaReceitas[tipoFonte-1] += valorCentavos;
		this.receitaTotal += valorCentavos;
		this.receitaAtual += valorCentavos;
	}
	
	/**
	 * Aumenta a despesa do aluno em uma quantia específica declarada, diminuindo, portanto, o valor da receita atual.
	 * 
	 * @param valorCentavos representa, em um valor inteiro de centavos, a quantidade a ser incrementada nas despesas.
	 */
	public void pagaDespesa(int valorCentavos) {
		this.despesas += valorCentavos;
		this.receitaAtual -= valorCentavos;
	}
	
	/**
	 * Aumenta a despesa do aluno em uma quantia específica declarada, diminuindo, portanto, o valor da receita atual. Além disso, é informado, em String, o detalhe da despesa em questão, que é armazenado o histórico de despesas.
	 * 
	 * @param valorCentavos representa, em um valor inteiro de centavos, a quantidade a ser incrementada nas despesas.
	 * @param detalhes representa, em String, os detalhes referente à despesa em questão.
	 */
	public void pagaDespesa(int valorCentavos, String detalhes) {
		this.despesas += valorCentavos;
		this.receitaAtual -= valorCentavos;
		
		String aux01 = detalhes;
		String aux02;

		for (int i = this.historicoDespesas.length-1; i >=0; i--) {
			aux02 = this.historicoDespesas[i];
			this.historicoDespesas[i] = aux01;
			aux01 = aux02;
		}
	}
	
	/**
	 * Retorna uma String que contém os últimos 5 detalhes informados pelo aluno quanto ao aumento de despesas. Caso não tenha sido informado até 5 detalhes, os campos referentes aos restantes permanecerão em branco, contendo apenas a contagem.
	 * 
	 * @return é a String referente ao detalhamento dos últimos 5 detalhes informados pelo aluno referentes ao aumento de despesas.
	 */
	public String listarDetalhes() {
		String retorno = "";
		int contador = 1;
		
		for (int i = this.historicoDespesas.length-1; i >= 0; i--) {
			if (this.historicoDespesas[i] != null) {
				retorno += contador + " - " + this.historicoDespesas[i];
				contador++;
				if (i != 0) {
					retorno += "\n";
				}
			}
		}
		
		return retorno;
	}
	
	/**
	 * Retorna uma String que mostra, em linhas diferentes, como anda a receita total de cada fonte financeira do aluno. Caso alguma ainda não tenha sido incrementada, o valor a ser retornado será zero.
	 * 
	 * @return é a String que mostra a receita total de cada fonte financeira.
	 */
	public String exibeFontes() {
		 String fontes = "";
		 
		 for (int i = 0; i < this.listaReceitas.length; i++) {
			 fontes += (i+1) + " - " + this.listaReceitas[i];
			 
			 if (i != this.listaReceitas.length - 1) {
				 fontes += "\n";
			 }
		 }
		 
		 return fontes;
	}
	
	/**
	 * Retorna uma String referente às informações gerais da situação financeira do aluno, mostrando valores como a receita total, a receita atual e despesas totais.
	 * 
	 * @return é a String referente às informações gerais do financeiro do aluno.
	 */
	@Override
	public String toString() {
		String retorno = "Receita total: " + this.receitaTotal + ", Receita atual: " + this.receitaAtual + ", Despesas totais: " + this.despesas;
		return retorno;
	}
	
}
