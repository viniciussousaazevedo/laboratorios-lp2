package ufcg.ccc.domino.estrategia;

import java.util.List;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Peca;
import ufcg.ccc.domino.Jogada.TipoJogada;

/**
 * ESTRATÉGIA FEITA PARA TESTES!
 * 
 * segue o mesmo padrão da estratégia "Joga primeira possível". Contudo, uma peça que encaixa em ambos os lados irá ser jogada
 * no lado ESQUERDO da mesa.
 *
 */
public class JogaPrimeiraPossivelAdaptada implements EstrategiaDeJogo {

	@Override
	public Jogada decideJogada(List<Peca> mao, VisaoDaMesa mesa) {
		if (mesa.getNumPecas() == 0) {
			return new Jogada(mao.get(0), TipoJogada.NA_ESQUERDA);
		}

		for (Peca peca : mao) {
			if (peca.encaixa(mesa.getNumNaEsquerda())) {
				return new Jogada(peca, TipoJogada.NA_ESQUERDA);
			}
			if (peca.encaixa(mesa.getNumNaDireita())) {
				return new Jogada(peca, TipoJogada.NA_DIREITA);
			}
		}

		return new Jogada();
	}

	@Override
	public String toString() {
		return "Joga Primeira Possível Adaptada";
	}

	
}
