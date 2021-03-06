package ufcg.ccc.domino;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import ufcg.ccc.domino.estrategia.EstrategiaInvalidaException;
import ufcg.ccc.domino.estrategia.JogaPrimeiraPossivel;

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
	void testJogoAleatorioComPecasPersonalizadas() throws Exception {
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), 10, new PersonalizacaoDePeca("a", "b", "c", "d", "e", "f", "g"));
		
		HistoricoDeJogo historico = j.jogaJogoCompleto();
		assertTrue(j.isFinalizado());
		System.out.println(historico.toString());
		
		j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), 10, new PersonalizacaoDePeca());
		
		historico = j.jogaJogoCompleto();
		assertTrue(j.isFinalizado());
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
	void testVencedorJ1PecasPersonalizadas() throws Exception {
		PersonalizacaoDePeca personalizado = new PersonalizacaoDePeca("a", "b", "c", "d", "e", "f", "g");
		
		List<Peca> mao1 = List.of(new Peca(0, 0, personalizado), new Peca(0, 1, personalizado));
		List<Peca> mao2 = List.of(new Peca(0, 2, personalizado), new Peca(0, 3, personalizado));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);

		assertFalse(j.isFinalizado());
		assertEquals(null, j.getVencedor());

		j.jogaJogoCompleto();
		
		assertTrue(j.isFinalizado());
		assertEquals("J1", j.getVencedor());
		
		
		personalizado = new PersonalizacaoDePeca();
		mao1 = List.of(new Peca(0, 0, personalizado), new Peca(0, 1, personalizado));
		mao2 = List.of(new Peca(0, 2, personalizado), new Peca(0, 3, personalizado));
		
		j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);

		assertFalse(j.isFinalizado());
		assertEquals(null, j.getVencedor());

		j.jogaJogoCompleto();
		
		assertTrue(j.isFinalizado());
		assertEquals("J1", j.getVencedor());
	}
	
	@Test
	void testEmpate() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 0), new Peca(4, 6));
		List<Peca> mao2 = List.of(new Peca(0, 1), new Peca(5, 5));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);

		j.jogaJogoCompleto();
		
		assertTrue(j.isFinalizado());
		assertNull(j.getVencedor());
	}
	
	@Test
	void testVitoriaJ2() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 0), new Peca(6, 6));
		List<Peca> mao2 = List.of(new Peca(0, 1), new Peca(1, 2));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);

		j.jogaJogoCompleto();
		
		assertTrue(j.isFinalizado());
		assertEquals("J2", j.getVencedor());
	}
	
	@Test
	void testVitoriaJ2PecasPersonalizadas() throws Exception {
		PersonalizacaoDePeca personalizado = new PersonalizacaoDePeca("a", "b", "c", "d", "e", "f", "g");
		
		List<Peca> mao1 = List.of(new Peca(0, 0, personalizado), new Peca(6, 6, personalizado));
		List<Peca> mao2 = List.of(new Peca(0, 1, personalizado), new Peca(1, 2, personalizado));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);

		j.jogaJogoCompleto();
		
		assertTrue(j.isFinalizado());
		assertEquals("J2", j.getVencedor());
		
		
		personalizado = new PersonalizacaoDePeca();
		
		mao1 = List.of(new Peca(0, 0, personalizado), new Peca(6, 6, personalizado));
		mao2 = List.of(new Peca(0, 1, personalizado), new Peca(1, 2, personalizado));
		
		j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);

		j.jogaJogoCompleto();
		
		assertTrue(j.isFinalizado());
		assertEquals("J2", j.getVencedor());
	}
}
