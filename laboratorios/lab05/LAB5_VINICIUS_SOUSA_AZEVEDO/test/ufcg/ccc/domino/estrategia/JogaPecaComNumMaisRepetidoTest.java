package ufcg.ccc.domino.estrategia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Jogada.TipoJogada;
import ufcg.ccc.domino.Mesa;
import ufcg.ccc.domino.Peca;

public class JogaPecaComNumMaisRepetidoTest {

	private Mesa mesa;
	private JogaPecaComNumMaisRepetido estrategia;
	
	@BeforeEach
	void setUp() throws Exception {
		mesa = new Mesa();
		mesa.jogaNaEsquerda(new Peca(4, 6));
		estrategia = new JogaPecaComNumMaisRepetido();
	}
	
	@Test
	void testMesaVazia() {
		Jogada j;
		mesa = new Mesa();
		
		// Todos os números se repetem uma vez
		j = estrategia.decideJogada(List.of(new Peca(0, 4), new Peca(6, 5), new Peca(2, 3)), mesa);
		assertEquals("6:5", j.getPeca().toString());
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
		
		// 4 é o que mais se repete (com carroça)
		j = estrategia.decideJogada(List.of(new Peca(0, 4), new Peca(6, 5), new Peca(4, 4), new Peca(1, 2)), mesa);
		assertEquals("4:4", j.getPeca().toString());
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
		
		// 4 é o que mais se repete (sem carroça)
		j = estrategia.decideJogada(List.of(new Peca(4, 0), new Peca(6, 4), new Peca(1, 5), new Peca(4, 5)), mesa);
		assertEquals("4:0", j.getPeca().toString());
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
		
		// 4 e 5 se repetem igualmente
		j = estrategia.decideJogada(List.of(new Peca(4, 4), new Peca(5, 0), new Peca(5, 2), new Peca(1, 6)), mesa);
		assertEquals("5:0", j.getPeca().toString());
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
	}
	
	@Test
	void testPrefereEsquerda() {
		Jogada j;
		
		j = estrategia.decideJogada(List.of(new Peca(6, 4)), mesa);
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
	}
	
	@Test
	void testPassa() {
		Jogada j;
		
		j = estrategia.decideJogada(List.of(new Peca(0, 1), new Peca(1, 2), new Peca(2, 2), new Peca(3, 5)), mesa);
		assertEquals(TipoJogada.PASSA, j.getTipo());
	}
	
	@Test
	void testNumerosComMesmaQuantidadeDeRepeticao() {
		Jogada j;
		
		j = estrategia.decideJogada(List.of(new Peca(4, 4), new Peca(4, 6), new Peca(6, 6), new Peca(1, 2)), mesa);
		assertEquals("6:6", j.getPeca().toString());
		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
	}
	
	@Test
	void testNumMaisRepetidoEncaixaComCarroca() {
		Jogada j;
		
		j = estrategia.decideJogada(List.of(new Peca(0, 1), new Peca(6, 2), new Peca(6, 6), new Peca(6, 4)), mesa);
		assertEquals("6:6", j.getPeca().toString());
		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
		
		j = estrategia.decideJogada(List.of(new Peca(4, 1), new Peca(6, 4), new Peca(1, 1), new Peca(4, 4)), mesa);
		assertEquals("4:4", j.getPeca().toString());
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
	}
	
	@Test
	void testNumMaisRepetidoEncaixaSemCarroca() {
		Jogada j;
		
		j = estrategia.decideJogada(List.of(new Peca(4, 1), new Peca(6, 2), new Peca(6, 5), new Peca(6, 4)), mesa);
		assertEquals("6:2", j.getPeca().toString());
		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
		
		j = estrategia.decideJogada(List.of(new Peca(4, 1), new Peca(6, 4), new Peca(1, 1), new Peca(4, 2)), mesa);
		assertEquals("4:1", j.getPeca().toString());
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
	}
	
	@Test
	void testNumMaisRepetidoNaoEncaixa() {
		Jogada j;
		
		j = estrategia.decideJogada(List.of(new Peca(6, 1), new Peca(2, 2), new Peca(2, 5), new Peca(6, 4)), mesa);
		assertEquals("6:1", j.getPeca().toString());
		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
		
		j = estrategia.decideJogada(List.of(new Peca(4, 4), new Peca(3, 2), new Peca(3, 1), new Peca(3, 0)), mesa);
		assertEquals("4:4", j.getPeca().toString());
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
	}
	
}
