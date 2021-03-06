package ufcg.ccc.domino.estrategia;

import java.util.Comparator;

import ufcg.ccc.domino.Peca;

/**
 * Compara as peças passadas como parâmetro quanto aos valores de seus lados em ordem crescente, priorizando
 * sempre o número esquerdo da peça, analisando, em segundo plano, os valores do lado direito
 * (isto é, com o lado esquerdo já priorizado, sem desrespeitá-lo)
 */
public class PecaPadraoComparator implements Comparator<Peca> {

	@Override
	public int compare(Peca o1, Peca o2) {
		if (o1.getNumEsquerdo() < o2.getNumEsquerdo()) {
			return -1;
		} else if (o1.getNumEsquerdo() > o2.getNumEsquerdo()) {
			return 1;
		}
		
		if (o1.getNumDireito() < o2.getNumDireito()) {
			return -1;
		} else if (o1.getNumDireito() > o2.getNumDireito()) {
			return 1;
		}
		return 0;
	}

}
