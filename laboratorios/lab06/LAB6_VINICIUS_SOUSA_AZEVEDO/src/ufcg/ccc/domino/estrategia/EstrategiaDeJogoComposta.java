package ufcg.ccc.domino.estrategia;

import java.util.ArrayList;
import java.util.List;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Peca;

/**
 * O usuário passa uma lista de estratégias das quais quer utilizar com o passar das rodadas, sendo executada
 * uma por vez, na sequência da lista informada.
 */
public class EstrategiaDeJogoComposta implements EstrategiaDeJogo {
	
	private ArrayList<EstrategiaDeJogo> estrategias;
	private int contador;
	
	public EstrategiaDeJogoComposta(ArrayList<EstrategiaDeJogo> estrategias) {
		this.estrategias = estrategias;
		this.contador = 0;
	}

	@Override
	public Jogada decideJogada(List<Peca> mao, VisaoDaMesa mesa) {
		
		Jogada jogada = this.estrategias.get(contador).decideJogada(mao, mesa);
		
		if (this.contador < this.estrategias.size()-1) {
			this.contador++;
		} else {
			this.contador = 0;
		}
		
		return jogada;
	}
	
	@Override
	public String toString() {
		return "Estratégia de jogos composta";
	}
}
