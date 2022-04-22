package ufcg.ccc.domino.estrategia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Jogada.TipoJogada;
import ufcg.ccc.domino.JogadaInvalidaException;
import ufcg.ccc.domino.Mesa;
import ufcg.ccc.domino.Peca;

class JogaPrimeiraPossivelComparadoraTest {

	private EstrategiaDeJogo estrategia;
	private Mesa mesa;
	
	@BeforeEach
	void setUp() throws JogadaInvalidaException {
		Comparator<Peca> ordenacao = new PecaPadraoComparator();
		this.estrategia = new JogaPrimeiraPossivelComparadora(ordenacao);
		this.mesa = new Mesa();
		this.mesa.jogaNaDireita(new Peca(2, 5));
	}
	
	@Test
	void testMesaVazia() {
		this.mesa = new Mesa();
		List<Peca> mao = new ArrayList<Peca>(List.of(new Peca(2, 3), new Peca(6, 5), new Peca(0, 4)));
		Jogada j = estrategia.decideJogada(mao, mesa);
		
		String pecas = "";
		for (Peca p : mao) {
			pecas += p.toString() + " ";
		}
		
		assertEquals("0:4 2:3 6:5 ", pecas);
		
		assertEquals("0:4", j.getPeca().toString());
		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
	}
	
	@Test
	void testPassa() {
		List<Peca> mao = new ArrayList<Peca>(List.of(new Peca(1, 3), new Peca(6, 4), new Peca(0, 4)));
		Jogada j = estrategia.decideJogada(mao, mesa);
		
		String pecas = "";
		for (Peca p : mao) {
			pecas += p.toString() + " ";
		}
		
		assertEquals("0:4 1:3 6:4 ", pecas);
		assertEquals(TipoJogada.PASSA, j.getTipo());
	}
	
	@Test
	void testJogaNaDireita() {
		List<Peca> mao = new ArrayList<Peca>(List.of(new Peca(5, 3), new Peca(6, 5), new Peca(0, 4)));
		Jogada j = estrategia.decideJogada(mao, mesa);
		
		String pecas = "";
		for (Peca p : mao) {
			pecas += p.toString() + " ";
		}
		
		assertEquals("0:4 5:3 6:5 ", pecas);
		
		assertEquals("5:3", j.getPeca().toString());
		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
	}
	
	@Test
	void testJogaNaEsquerda() {
		List<Peca> mao = new ArrayList<Peca>(List.of(new Peca(5, 3), new Peca(6, 5), new Peca(2, 4)));
		Jogada j = estrategia.decideJogada(mao, mesa);
		
		String pecas = "";
		for (Peca p : mao) {
			pecas += p.toString() + " ";
		}
		
		assertEquals("2:4 5:3 6:5 ", pecas);
		
		assertEquals("2:4", j.getPeca().toString());
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
	}
	
	@Test
	void prefereNaDireita() {
		List<Peca> mao = new ArrayList<Peca>(List.of(new Peca(6, 3), new Peca(1, 3), new Peca(2, 5)));
		Jogada j = estrategia.decideJogada(mao, mesa);
		
		String pecas = "";
		for (Peca p : mao) {
			pecas += p.toString() + " ";
		}
		
		assertEquals("1:3 2:5 6:3 ", pecas);
		
		assertEquals("2:5", j.getPeca().toString());
		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
	}
}
