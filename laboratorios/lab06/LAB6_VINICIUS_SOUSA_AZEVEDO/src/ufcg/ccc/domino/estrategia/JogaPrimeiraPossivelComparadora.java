package ufcg.ccc.domino.estrategia;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Peca;

public class JogaPrimeiraPossivelComparadora implements EstrategiaDeJogo {

	private Comparator<Peca> pecas;
	private EstrategiaDeJogo estrategia;
	
	public JogaPrimeiraPossivelComparadora(Comparator<Peca> pecas) {
		this.pecas = pecas;
		this.estrategia = new JogaPrimeiraPossivel();
	}
	
	@Override
	public Jogada decideJogada(List<Peca> mao, VisaoDaMesa mesa) {
		Collections.sort(mao, pecas);
		return this.estrategia.decideJogada(mao, mesa);
	}

	@Override
	public String toString() {
		return "Joga Primeira Possível Comparadora";
	}
}
