package ufcg.ccc.domino;

import ufcg.ccc.domino.estrategia.EstrategiaDeJogo;
import ufcg.ccc.domino.estrategia.EstrategiaInvalidaException;
import ufcg.ccc.domino.estrategia.JogaPecaComNumMaisRepetido;
import ufcg.ccc.domino.estrategia.JogaPrimeiraCarrocaPossivel;
import ufcg.ccc.domino.estrategia.JogaPrimeiraPossivel;

/**
 * Exemplo de como fazer um um main com uma disputa de muuuitos jogos entre duas
 * estratégias.
 * 
 */
public class DominoBrutalRepetido {
	
	private static final int NUM_PECAS_INICIAL = 12;
	private static final int REPETICOES = 50000;

	public static void main(String[] args) throws EstrategiaInvalidaException, JogadaInvalidaException {
		
		jogaVariasVezes(new JogaPrimeiraPossivel(), new JogaPrimeiraPossivel());
		
	}

	public static void jogaVariasVezes(EstrategiaDeJogo e1, EstrategiaDeJogo e2) throws EstrategiaInvalidaException, JogadaInvalidaException {
		float empates = 0;
		float vitoriasJ1 = 0, pontosJ1 = 0, batidasNormaisJ1 = 0, desempatesJ1 = 0, carrocasJ1 = 0, laLoJ1 = 0, quadradasJ1 = 0;
		float vitoriasJ2 = 0, pontosJ2 = 0, batidasNormaisJ2 = 0, desempatesJ2 = 0, carrocasJ2 = 0, laLoJ2 = 0, quadradasJ2 = 0;
		
		for (int i = 0; i < REPETICOES; i++) {
			
			Jogo j;
			
			// Cada estratégia começa jogando metade das partidas.
			if( i < REPETICOES / 2) {
				j = new Jogo("J1", e1, "J2", e2, NUM_PECAS_INICIAL);
			} else {
				j = new Jogo("J2", e2, "J1", e1, NUM_PECAS_INICIAL);
			}
			
			HistoricoDeJogo historico = j.jogaJogoCompleto();
			if (historico.isEmpate()) {
				empates++;
			} else if (historico.getVencedor() == "J1") {
				vitoriasJ1++;
				pontosJ1 += historico.getPontosVencedor();
				
				switch (historico.getPontosVencedor()) {
				case 1:
					if (historico.isVitoriaPorDesempate()) {
						desempatesJ1++;
					} else {
						batidasNormaisJ1++;
					}
					break;
				case 2:
					carrocasJ1++;
					break;
				case 3:
					laLoJ1++;
					break;
				case 6:
					quadradasJ1++;
					break;
				default:
					throw new RuntimeException("Formação de pontos inválida para este jogo: " + historico.getPontosVencedor());
				}
				
			} else if (historico.getVencedor() == "J2") {
				vitoriasJ2++;
				pontosJ2 += historico.getPontosVencedor();
				
				switch (historico.getPontosVencedor()) {
				case 1:
					if (historico.isVitoriaPorDesempate()) {
						desempatesJ2++;
					} else {
						batidasNormaisJ2++;
					}
					break;
				case 2:
					carrocasJ2++;
					break;
				case 3:
					laLoJ2++;
					break;
				case 6:
					quadradasJ2++;
					break;
				default:
					throw new IllegalArgumentException("Formação de pontos inválida para este jogo: " + historico.getPontosVencedor());
				}
			}
		}

		System.out.println(
				"E1: " + e1.toString() 
				+ "\nE2: " + e2.toString()
				+ "\nJogos: " + (REPETICOES)
				+ "\nObs: Porcentagens em valores aproximados."
				+ "\n\n- Vitórias E1: " + vitoriasJ1 + " (" + Math.round(vitoriasJ1 / REPETICOES * 100) + "%)"
				+ "\n- Quantidade de pontos: " + pontosJ1
				+ "\n=== TIPOS DE VITÓRIA DO JOGADOR ==="
				+ "\nDesempate:\t " + desempatesJ1 + "\t(" + Math.round(desempatesJ1 / vitoriasJ1 * 100) + "%)"
				+ "\nBatidas normais: " + batidasNormaisJ1 + "\t(" + Math.round(batidasNormaisJ1 / vitoriasJ1 * 100) + "%)"
				+ "\nCarroça:\t " + carrocasJ1 + "\t\t(" + Math.round(carrocasJ1 / vitoriasJ1 * 100) + "%)"
				+ "\nLá e ló:\t " + laLoJ1 + "\t\t(" + Math.round(laLoJ1 / vitoriasJ1 * 100) + "%)"
				+ "\nQuadrada:\t " + quadradasJ1 + "\t\t(" + Math.round(quadradasJ1 / vitoriasJ1 * 100) + "%)"
				+ "\n\n- Vitórias E2: " + vitoriasJ2 + " (" + Math.round(vitoriasJ2 / REPETICOES * 100) + "%)"
				+ "\n- Quantidade de pontos: " + pontosJ2
				+ "\n=== TIPOS DE VITÓRIA DO JOGADOR ==="
				+ "\nDesempate:\t " + desempatesJ2 + "\t(" + Math.round(desempatesJ2 / vitoriasJ2 * 100) + "%)"
				+ "\nBatidas normais: " + batidasNormaisJ2 + "\t\t(" + Math.round(batidasNormaisJ2 / vitoriasJ2 * 100) + "%)"
				+ "\nCarroça:\t " + carrocasJ2 + "\t\t(" + Math.round(carrocasJ2 / vitoriasJ2 * 100) + "%)"
				+ "\nLá e ló:\t " + laLoJ2 + "\t\t(" + Math.round(laLoJ2 / vitoriasJ2 * 100) + "%)"
				+ "\nQuadrada:\t " + quadradasJ2 + "\t\t(" + Math.round(quadradasJ2 / vitoriasJ2 * 100) + "%)"
				+ "\n\n- Empates: " + empates + " (" + Math.round(empates / REPETICOES * 100) + "%)");
	}
	
}
