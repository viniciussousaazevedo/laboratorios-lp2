package ufcg.ccc.domino;

import java.util.HashMap;

/**
 * Uma peça de dominó com dois lados.
 *
 */
public class Peca {

	private int numEsquerdo;
	private int numDireito;
	private HashMap<Integer, String> regraDePersonalizacao;

	/**
	 * Cria uma peça.
	 * 
	 * @param numEsquerdo Número do lado esquerdo.
	 * @param numDireito  Número do lado direito.
	 */
	public Peca(int numEsquerdo, int numDireito) {
		this.numEsquerdo = numEsquerdo;
		this.numDireito = numDireito;
		this.regraDePersonalizacao = new PersonalizacaoDePeca().getRegraDePersonalizacao();
	}
	
	/**
	 * Cria uma peça com uma regra de personalização especificada pelo usuário.
	 * 
	 * @param numEsquerdo 			Número do lado esquerdo.
	 * @param numDireito  			Número do lado direito.
	 * @param personalizacaoDePeca  Objeto que contém a regra de personalização da peça.
	 */
	public Peca(int numEsquerdo, int numDireito, PersonalizacaoDePeca personalizacaoDePeca) {
		this.numEsquerdo = numEsquerdo;
		this.numDireito = numDireito;
		this.regraDePersonalizacao = personalizacaoDePeca.getRegraDePersonalizacao();
	}

	/**
	 * Inverte os lados dos números na peça.
	 */
	public void gira() {
		int tmp = numEsquerdo;
		numEsquerdo = numDireito;
		numDireito = tmp;
	}

	/**
	 * 
	 * @return O número da direita.
	 */
	public int getNumDireito() {
		return numDireito;
	}
	
	/**
	 * 
	 * @return O valor personalizado do número da direita.
	 */
	public String getNumDireitoPersonalizado() {
		return this.regraDePersonalizacao.get(this.numDireito);
	}
	
	/**
	 * 
	 * @return O número da esquerda.
	 */
	public int getNumEsquerdo() {
		return numEsquerdo;
	}
	
	/**
	 * 
	 * @return O valor personalizado do número da esquerda.
	 */
	public String getNumEsquerdoPersonalizado() {
		return this.regraDePersonalizacao.get(this.numEsquerdo);
	}

	@Override
	public String toString() {
		return this.getNumEsquerdoPersonalizado() + ":" + this.getNumDireitoPersonalizado();
	}

	/**
	 * Testa se a peça encaixa com um número.
	 * 
	 * @param numero O número a testar.
	 * @return true se um dos lados ao menos combinar com o númer.
	 */
	public boolean encaixa(int numero) {
		return this.numDireito == numero || this.numEsquerdo == numero;
	}

}
