package ufcg.ccc.domino;



import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import ufcg.ccc.domino.Jogada.TipoJogada;
import ufcg.ccc.domino.estrategia.EstrategiaInvalidaException;
import ufcg.ccc.domino.estrategia.JogaPrimeiraPossivel;
import ufcg.ccc.domino.estrategia.JogaPrimeiraPossivelAdaptada;

class JogoTest {

	@Test
	void testRodadaInicial() throws JogadaInvalidaException, EstrategiaInvalidaException {
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), 14);

		assertEquals(0, j.getNumRodadas());
		assertEquals(14, j.getNumPecasJ1());
		assertEquals(14, j.getNumPecasJ2());

		j.rodada();

		assertEquals(1, j.getNumRodadas());
		assertEquals(13, j.getNumPecasJ1());
		assertEquals(13, j.getNumPecasJ2());
	}
	
	@Test
	void testJogoAleatorio() throws JogadaInvalidaException, EstrategiaInvalidaException {
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), 10, new Random(1));

		HistoricoDeJogo historico = j.jogaJogoCompleto();

		assertTrue(j.isFinalizado());
		assertEquals("J1", j.getVencedor());
		
		System.out.println(historico.toString());
	}

	@Test
	void testVencedorJ1Simples() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 0), new Peca(0, 1));
		List<Peca> mao2 = List.of(new Peca(0, 2), new Peca(0, 3));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);

		assertFalse(j.isFinalizado());
		assertEquals(null, j.getVencedor());

		j.jogaJogoCompleto();
		
		assertTrue(j.isFinalizado());
		assertEquals("J1", j.getVencedor());
	}
	
	@Test
	void testVencedorJ1MenosPecas() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 0), new Peca(2, 2));
		List<Peca> mao2 = List.of(new Peca(0, 1), new Peca(5, 3), new Peca(4, 5));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		
		assertFalse(j.isFinalizado());
		assertNull(j.getVencedor());
		
		j.jogaJogoCompleto();
		
		assertTrue(j.isFinalizado());
		assertEquals("J1", j.getVencedor());
	}
	
	@Test
	void testVencedorJ1MenorSoma() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 0), new Peca(2, 2));
		List<Peca> mao2 = List.of(new Peca(0, 1), new Peca(5, 3));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		
		assertFalse(j.isFinalizado());
		assertNull(j.getVencedor());
		
		j.jogaJogoCompleto();
		
		assertTrue(j.isFinalizado());
		assertEquals("J1", j.getVencedor());
	}
	
	@Test
	void testEmpate() throws Exception {
		List<Peca> mao1 = List.of(new Peca(1, 1), new Peca(6, 0));
		List<Peca> mao2 = List.of(new Peca(1, 1), new Peca(3, 3));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		j.jogaJogoCompleto();
		
		assertTrue(j.isFinalizado());
		assertNull(j.getVencedor());
		
		mao1 = List.of(new Peca(1, 1), new Peca(2, 3));
		mao2 = List.of(new Peca(1, 1), new Peca(3, 2));
		
		j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		j.jogaJogoCompleto();
		
		assertTrue(j.isFinalizado());
		assertNull(j.getVencedor());
	}
	
	@Test
	void testVitoriaJ2Simples() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 0), new Peca(6, 6));
		List<Peca> mao2 = List.of(new Peca(0, 1), new Peca(1, 2));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);

		j.jogaJogoCompleto();
		
		assertTrue(j.isFinalizado());
		assertEquals("J2", j.getVencedor());
	}
	
	@Test
	void testVencedorJ2MenosPecas() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 1), new Peca(5, 3), new Peca(4, 5));
		List<Peca> mao2 = List.of(new Peca(0, 0), new Peca(2, 2));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		
		assertFalse(j.isFinalizado());
		assertNull(j.getVencedor());
		
		j.jogaJogoCompleto();
		
		assertTrue(j.isFinalizado());
		assertEquals("J2", j.getVencedor());
	}
	
	@Test
	void testVencedorJ2MenorSoma() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 1), new Peca(5, 3));
		List<Peca> mao2 = List.of(new Peca(0, 0), new Peca(2, 2));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		
		assertFalse(j.isFinalizado());
		assertNull(j.getVencedor());
		
		j.jogaJogoCompleto();
		
		assertTrue(j.isFinalizado());
		assertEquals("J2", j.getVencedor());
	}
	
	@Test
	void testPontuacaoDesempateMenosPecas() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 1), new Peca(5, 3));
		List<Peca> mao2 = List.of(new Peca(0, 0), new Peca(2, 2), new Peca(6, 6));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		HistoricoDeJogo historico = j.jogaJogoCompleto();
		
		assertEquals("J1", historico.getVencedor());
		assertEquals(TipoJogada.PASSA, historico.getJogadaVencedora().getTipo());
		assertEquals(1, historico.getPontosVencedor());
		
	}
	
	@Test
	void testPontuacaoDesempateMenorSoma() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 1), new Peca(2, 2));
		List<Peca> mao2 = List.of(new Peca(0, 0), new Peca(5, 3));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		HistoricoDeJogo historico = j.jogaJogoCompleto();
		
		assertEquals("J1", historico.getVencedor());
		assertEquals(TipoJogada.PASSA, historico.getJogadaVencedora().getTipo());
		assertEquals(1, historico.getPontosVencedor());
	}
	
	@Test
	void testPontuacaoBatidaSimplesEsquerda() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 1), new Peca(2, 4));
		List<Peca> mao2 = List.of(new Peca(0, 2), new Peca(4, 3));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		HistoricoDeJogo historico = j.jogaJogoCompleto();
		
		assertEquals("J1", historico.getVencedor());
		assertEquals(TipoJogada.NA_ESQUERDA, historico.getJogadaVencedora().getTipo());
		assertEquals(1, historico.getPontosVencedor());
	}
	
	@Test
	void testPontuacaoBatidaSimplesDireita() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 1), new Peca(1, 4));
		List<Peca> mao2 = List.of(new Peca(0, 2), new Peca(6, 6));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		HistoricoDeJogo historico = j.jogaJogoCompleto();
		
		assertEquals("J1", historico.getVencedor());
		assertEquals(TipoJogada.NA_DIREITA, historico.getJogadaVencedora().getTipo());
		assertEquals(1, historico.getPontosVencedor());
	}
	
	@Test
	void testPontuacaoCarrocaEsquerda() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 1), new Peca(2, 2));
		List<Peca> mao2 = List.of(new Peca(2, 0), new Peca(4, 6));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		HistoricoDeJogo historico = j.jogaJogoCompleto();
		
		assertEquals("J1", historico.getVencedor());
		assertEquals(TipoJogada.NA_ESQUERDA, historico.getJogadaVencedora().getTipo());
		assertEquals(2, historico.getPontosVencedor());
	}
	
	@Test
	void testPontuacaoCarrocaDireita() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 1), new Peca(1, 1));
		List<Peca> mao2 = List.of(new Peca(2, 0), new Peca(6, 5));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		HistoricoDeJogo historico = j.jogaJogoCompleto();
		
		assertEquals("J1", historico.getVencedor());
		assertEquals(TipoJogada.NA_DIREITA, historico.getJogadaVencedora().getTipo());
		assertEquals(2, historico.getPontosVencedor());
	}
	
	@Test
	void testPontuacaoLaLoEsquerda() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 1), new Peca(1, 2));
		List<Peca> mao2 = List.of(new Peca(2, 0), new Peca(6, 5));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivelAdaptada(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		HistoricoDeJogo historico = j.jogaJogoCompleto();
		
		assertEquals("J1", historico.getVencedor());
		assertEquals(TipoJogada.NA_ESQUERDA, historico.getJogadaVencedora().getTipo());
		assertEquals(3, historico.getPontosVencedor());
		
		mao1 = List.of(new Peca(0, 1), new Peca(0, 6));
		mao2 = List.of(new Peca(1, 0), new Peca(4, 5));
		
		j = new Jogo("J1", new JogaPrimeiraPossivelAdaptada(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		historico = j.jogaJogoCompleto();
		
		assertEquals("J1", historico.getVencedor());
		assertEquals(TipoJogada.NA_ESQUERDA, historico.getJogadaVencedora().getTipo());
		assertEquals(3, historico.getPontosVencedor());
	}
	
	@Test
	void testPontuacaoLaLoDireita() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 1), new Peca(1, 2));
		List<Peca> mao2 = List.of(new Peca(2, 0), new Peca(3, 2));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		HistoricoDeJogo historico = j.jogaJogoCompleto();
		
		assertEquals("J1", historico.getVencedor());
		assertEquals(TipoJogada.NA_DIREITA, historico.getJogadaVencedora().getTipo());
		assertEquals(3, historico.getPontosVencedor());
		
		mao1 = List.of(new Peca(0, 1), new Peca(0, 5));
		mao2 = List.of(new Peca(1, 0), new Peca(4, 5));
		
		j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		historico = j.jogaJogoCompleto();
		
		assertEquals("J1", historico.getVencedor());
		assertEquals(TipoJogada.NA_DIREITA, historico.getJogadaVencedora().getTipo());
		assertEquals(3, historico.getPontosVencedor());
	}
	
	@Test
	void testPontuacaoQuadradaEsquerda() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 6), new Peca(0, 0));
		List<Peca> mao2 = List.of(new Peca(6, 0), new Peca(2, 5));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivelAdaptada(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		HistoricoDeJogo historico = j.jogaJogoCompleto();
		
		assertEquals("J1", historico.getVencedor());
		assertEquals(TipoJogada.NA_ESQUERDA, historico.getJogadaVencedora().getTipo());
		assertEquals(6, historico.getPontosVencedor());
	}
	
	@Test
	void testPontuacaoQuadradaDireita() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 6), new Peca(0, 0));
		List<Peca> mao2 = List.of(new Peca(6, 0), new Peca(2, 5));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		HistoricoDeJogo historico = j.jogaJogoCompleto();
		
		assertEquals("J1", historico.getVencedor());
		assertEquals(TipoJogada.NA_DIREITA, historico.getJogadaVencedora().getTipo());
		assertEquals(6, historico.getPontosVencedor());
	}
}
