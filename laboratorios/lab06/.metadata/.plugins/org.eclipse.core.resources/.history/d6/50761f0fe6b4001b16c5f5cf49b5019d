package ufcg.ccc.domino.estrategia;

import java.util.List;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Jogada.TipoJogada;
import ufcg.ccc.domino.Peca;

/**
 * Observa a mesa da esquerda para a direita para cada peça, jogando sempre a primeira carroça possível (analisando sua mão também da esquerda para a direita).
 * Caso não tenha carroça ou a(s) carroça(s) não encaixe(m), o jogador escolhe a peça mais a esquerda possível de sua mão.
 */
public class JogaPrimeiraCarrocaPossivel implements EstrategiaDeJogo {
	
	@Override
	public Jogada decideJogada(List<Peca> mao, VisaoDaMesa mesa) {
		Peca pecaEscolhida = null;
		TipoJogada ladoEscolhido = TipoJogada.NA_ESQUERDA;
		
		if (mesa.getNumPecas() == 0) {
			pecaEscolhida = mao.get(0);
			for (Peca p : mao) {
				if (p.getNumEsquerdo() == p.getNumDireito()) {
					pecaEscolhida = p;
					break;
				}
			}
			return new Jogada(pecaEscolhida, ladoEscolhido);
		}
			
		for (Peca p : mao) {
			if (p.encaixa(mesa.getNumNaEsquerda()) || p.encaixa(mesa.getNumNaDireita())) {
				if (p.getNumEsquerdo() == p.getNumDireito()) {
					ladoEscolhido = (p.encaixa(mesa.getNumNaEsquerda()) ? TipoJogada.NA_ESQUERDA : TipoJogada.NA_DIREITA);
					pecaEscolhida = p;
					return new Jogada(pecaEscolhida, ladoEscolhido);
				}
					
				if (pecaEscolhida == null) {
					ladoEscolhido = (p.encaixa(mesa.getNumNaEsquerda()) ? TipoJogada.NA_ESQUERDA : TipoJogada.NA_DIREITA);
					pecaEscolhida = p;
				}	
			}
		}
		if (pecaEscolhida == null) {
			return new Jogada();
		}
		return new Jogada(pecaEscolhida, ladoEscolhido);
	}
	
	@Override
	public String toString() {
		return "Joga Primeira Carroça Possível";
	}
}		

