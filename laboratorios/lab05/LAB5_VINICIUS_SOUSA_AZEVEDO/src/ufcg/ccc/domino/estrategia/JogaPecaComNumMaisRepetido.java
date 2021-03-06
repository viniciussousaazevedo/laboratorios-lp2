package ufcg.ccc.domino.estrategia;

import java.util.HashMap;
import java.util.List;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Peca;
import ufcg.ccc.domino.Jogada.TipoJogada;

/**
 * O jogador analisa toda sua mão, procurando qual o número entre as peças que mais se repete. Com o número identificado, o jogador agora
 * escolhe a próxima peça a jogar de acordo com a seguinte prioridade:
 * 1 - Carroça do número mais repetido;
 * 2 - Peças que contenham o número repetido mais a esquerda de sua mão;
 * 3 - Peças que não possuem o número repetido mais a esquerda de sua mão.
 * 
 * Caso a peça escolhida encaixe em ambos os lados da mesa, o jogador irá preferir jogar no lado esquerdo.
 * Caso haja mais de um número com a maior quantidade de repetições, será escolhido o maior número.
 */
public class JogaPecaComNumMaisRepetido implements EstrategiaDeJogo {

	@Override
	public Jogada decideJogada(List<Peca> mao, VisaoDaMesa mesa) {
		int numMaisRepetido = this.identificaNumMaisRepetido(mao);
		TipoJogada ladoEscolhido = TipoJogada.NA_ESQUERDA;
		Peca pecaEscolhida = null;
		
		if (mesa.getNumPecas() == 0) {
			pecaEscolhida = mao.get(0);
			for (Peca p : mao) {
				if (p.getNumEsquerdo() == numMaisRepetido && p.getNumDireito() == numMaisRepetido) {
					pecaEscolhida = p;
					return new Jogada(pecaEscolhida, ladoEscolhido);
				}
				if (p.encaixa(numMaisRepetido) && !pecaEscolhida.encaixa(numMaisRepetido)) {
					pecaEscolhida = p;
				}
			}
			return new Jogada(pecaEscolhida, ladoEscolhido);
		}
		
		for (Peca p : mao) {
			if (p.encaixa(mesa.getNumNaEsquerda()) || p.encaixa(mesa.getNumNaDireita())) {
				TipoJogada ladoAtual = (p.encaixa(mesa.getNumNaEsquerda()) ? TipoJogada.NA_ESQUERDA : TipoJogada.NA_DIREITA);
				if (p.getNumEsquerdo() == numMaisRepetido && p.getNumDireito() == numMaisRepetido) {
					pecaEscolhida = p;
					ladoEscolhido = ladoAtual;
					return new Jogada(pecaEscolhida, ladoEscolhido);
				}
				
				if (pecaEscolhida == null) {
					pecaEscolhida = p;
					ladoEscolhido = ladoAtual;
				} else if (p.encaixa(numMaisRepetido) && !pecaEscolhida.encaixa(numMaisRepetido)) {
					pecaEscolhida = p;
					ladoEscolhido = ladoAtual;
				}
			}
		}
		if (pecaEscolhida == null) {
			return new Jogada();
		}
		return new Jogada(pecaEscolhida, ladoEscolhido);
		
	}
	
	private int identificaNumMaisRepetido(List<Peca> mao) {
		HashMap<Integer, Integer> numeros = new HashMap<Integer, Integer>();
		int maiorNumero = 0;
		int repeticoesNumero = 0;
		
		for (int i = 0; i <= 6; i++) {
			numeros.put(i, 0);
		}
		
		for (Peca p : mao) {
			numeros.put(p.getNumEsquerdo(), numeros.get(p.getNumEsquerdo())+1);
			numeros.put(p.getNumDireito(), numeros.get(p.getNumDireito())+1);
		}
		
		for (int n : numeros.keySet()) {
			if (numeros.get(n) >= repeticoesNumero) {
				maiorNumero = n;
				repeticoesNumero = numeros.get(n);
			}
		}
		return maiorNumero;
	}
	
	public String toString() {
		return "Joga Peça Com Número Mais Repetido";
	}

}
