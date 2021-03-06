package ufcg.ccc.domino;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * É onde o usuário informa um novo mapeamento de valores das peças do jogo para a existência de peças personalizadas
 */
public class PersonalizacaoDePeca {

	private HashMap<Integer, String> regraDePersonalizacao;
	
	/**
	 * Constrói a personalização da peça com os valores comuns de peças de dominó. É usado caso o usuário não declare um novo
	 * mapeamento para valores das peças.
	 */
	public PersonalizacaoDePeca() {
		this.regraDePersonalizacao = new HashMap<>();
		for (int i = 0; i <= 6; i++) {
			this.regraDePersonalizacao.put(i, Integer.toString(i));
		}
	}
	
	/**
	 * Constrói a personalização da peça com os valores personalizados declarados pelo usuário
	 * 
	 * @param num0 Valor personalizado do zero de uma peça.
	 * @param num1 Valor personalizado do um de uma peça. 
	 * @param num2 Valor personalizado do dois de uma peça.
	 * @param num3 Valor personalizado do três de uma peça.
	 * @param num4 Valor personalizado do quatro de uma peça.
	 * @param num5 Valor personalizado do cinco de uma peça.
	 * @param num6 Valor personalizado do seis de uma peça.
	 */
	public PersonalizacaoDePeca(String num0, String num1, String num2, String num3, String num4, String num5, String num6) {
		this.regraDePersonalizacao = new HashMap<>();
		this.regraDePersonalizacao.put(0, num0); this.regraDePersonalizacao.put(1, num1);
		this.regraDePersonalizacao.put(2, num2); this.regraDePersonalizacao.put(3, num3);
		this.regraDePersonalizacao.put(4, num4); this.regraDePersonalizacao.put(5, num5);
		this.regraDePersonalizacao.put(6, num6);
	}
	
	/**
	 * 
	 * @return o mapeamento de novos valores associado aos valores comuns das peças do dominó.
	 */
	public HashMap<Integer, String> getRegraDePersonalizacao() {
		return this.regraDePersonalizacao;
	}
	
	/**
	 * Cria o dominó com peças personalizadas.
	 * 
	 * @return Conjunto de 28 peças personalizadas, que, internamente, variam de 0:0 a 6:6.
	 */
	public List<Peca> criaPecas() {
		List<Peca> pecas = new LinkedList<Peca>();

		for (int i = 0; i <= 6; i++) {
			for (int j = i; j <= 6; j++) {
				pecas.add(new Peca(i, j, this));
			}
		}

		return pecas;
	}
	
}
