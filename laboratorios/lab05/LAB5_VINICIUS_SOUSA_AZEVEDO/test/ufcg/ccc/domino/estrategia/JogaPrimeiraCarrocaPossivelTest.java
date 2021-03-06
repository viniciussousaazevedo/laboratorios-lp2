package ufcg.ccc.domino.estrategia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Jogada.TipoJogada;
import ufcg.ccc.domino.Mesa;
import ufcg.ccc.domino.Peca;

class JogaPrimeiraCarrocaPossivelTest {

	private Mesa mesa;
	private JogaPrimeiraCarrocaPossivel estrategia;
	
	@BeforeEach
	void setUp() {
		mesa = new Mesa();
		estrategia = new JogaPrimeiraCarrocaPossivel();
	}
	
	@Test
	void testJogaMesaVazia() throws Exception {
		Jogada j = estrategia.decideJogada(List.of(new Peca(0, 4), new Peca(6, 5)), mesa);
		assertEquals("0:4", j.getPeca().toString());
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
		
		j = estrategia.decideJogada(List.of(new Peca(0, 4), new Peca(5, 5)), mesa);
		assertEquals("5:5", j.getPeca().toString());
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
		
		j = estrategia.decideJogada(List.of(new Peca(5, 5), new Peca(0, 4)), mesa);
		assertEquals("5:5", j.getPeca().toString());
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
	}
	
	@Test
	void prefereEsquerda() throws Exception {
		mesa.jogaNaEsquerda(new Peca(0, 4));
		Jogada j = estrategia.decideJogada(List.of(new Peca(0, 4)), mesa);
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
	}
	
	@Test
	void testJogaCarrocaNaEsquerda() throws Exception {
		mesa.jogaNaEsquerda(new Peca(0, 4));
		Jogada j = estrategia.decideJogada(List.of(new Peca(0, 0), new Peca(0, 6), new Peca(4, 4)), mesa);
		assertEquals("0:0", j.getPeca().toString());
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
		
		j = estrategia.decideJogada(List.of(new Peca(0, 6), new Peca(0, 0), new Peca(2, 3)), mesa);
		assertEquals("0:0", j.getPeca().toString());
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
		
		j = estrategia.decideJogada(List.of(new Peca(0, 6), new Peca(2, 3), new Peca(0, 0)), mesa);
		assertEquals("0:0", j.getPeca().toString());
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
	}
	
	@Test
	void testJogaCarrocaNaDireita() throws Exception {
		mesa.jogaNaEsquerda(new Peca(0, 4));
		Jogada j = estrategia.decideJogada(List.of(new Peca(4, 4), new Peca(0, 3), new Peca(0, 0)), mesa);
		assertEquals("4:4", j.getPeca().toString());
		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
		
		j = estrategia.decideJogada(List.of(new Peca(0, 3), new Peca(4, 4), new Peca(2, 3)), mesa);
		assertEquals("4:4", j.getPeca().toString());
		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
		
		j = estrategia.decideJogada(List.of(new Peca(4, 0), new Peca(1, 3), new Peca(4, 4)), mesa);
		assertEquals("4:4", j.getPeca().toString());
		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
	}
	
	@Test
	void testJogaNormalNaEsquerda() throws Exception {
		mesa.jogaNaEsquerda(new Peca(0, 4));
		Jogada j;
		
		j = estrategia.decideJogada(List.of(new Peca(0, 6), new Peca(0, 3), new Peca(5, 5)), mesa);
		assertEquals("0:6", j.getPeca().toString());
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
	}
	
	@Test
	void testJogaNormalNaDireita() throws Exception {
		mesa.jogaNaEsquerda(new Peca(0, 4));
		Jogada j = estrategia.decideJogada(List.of(new Peca(2, 3), new Peca(5, 5), new Peca(1, 3), new Peca(1, 4)), mesa);
		assertEquals("1:4", j.getPeca().toString());
		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
		
		j = estrategia.decideJogada(List.of(new Peca(2, 3), new Peca(5, 5), new Peca(1, 3), new Peca(1, 4), new Peca(0, 4)), mesa);
		assertEquals("1:4", j.getPeca().toString());
		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
	}
	
	@Test
	void testPassa() throws Exception {
		mesa.jogaNaEsquerda(new Peca(0, 4));
		Jogada j = estrategia.decideJogada(List.of(new Peca(2, 3), new Peca(1, 5), new Peca(6, 6), new Peca(5, 5)), mesa);
		assertEquals(TipoJogada.PASSA, j.getTipo());
	}
}
