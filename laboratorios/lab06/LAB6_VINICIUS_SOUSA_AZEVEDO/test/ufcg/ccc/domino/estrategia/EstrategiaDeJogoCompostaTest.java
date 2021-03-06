package ufcg.ccc.domino.estrategia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Jogada.TipoJogada;
import ufcg.ccc.domino.JogadaInvalidaException;
import ufcg.ccc.domino.Mesa;
import ufcg.ccc.domino.Peca;

class EstrategiaDeJogoCompostaTest {

	private EstrategiaDeJogo estrategia;
	private Mesa mesa;
	
	@BeforeEach
	void setUp() throws JogadaInvalidaException {
		ArrayList<EstrategiaDeJogo> estrategias = new ArrayList<>(List.of(new JogaPrimeiraPossivel(), new JogaPrimeiraCarrocaPossivel(), new JogaPrimeiraCarrocaPossivel()));
		estrategia = new EstrategiaDeJogoComposta(estrategias);
		this.mesa = new Mesa();
	}
	
	// foi feito os testes todos em um único método para observar a variação de estratégias da classe
	@Test
	void testAnalisaJogadas() throws JogadaInvalidaException {
		Jogada j;
		
		// Joga primeira possível (mesa vazia)
		j = estrategia.decideJogada(List.of(new Peca(3, 5), new Peca(3, 0), new Peca(6, 6), new Peca(0, 0)), mesa);
		assertEquals("3:5", j.getPeca().toString());
		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
		
		this.mesa.jogaNaDireita(new Peca(0, 6));
		
		// Joga primeira carroça possível
		j = estrategia.decideJogada(List.of(new Peca(0, 3), new Peca(4, 3), new Peca(6, 6), new Peca(0, 0)), mesa);
		assertEquals("6:6", j.getPeca().toString());
		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
		
		// Joga primeira carroça possível
		j = estrategia.decideJogada(List.of(new Peca(4, 6), new Peca(0, 0), new Peca(6, 6), new Peca(0, 0)), mesa);
		assertEquals("0:0", j.getPeca().toString());
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());

		// Joga primeira possível
		j = estrategia.decideJogada(List.of(new Peca(2, 2), new Peca(3, 3), new Peca(4, 5), new Peca(0, 6)), mesa);
		assertEquals("0:6", j.getPeca().toString());
		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
		
		// Joga primeira carroça possível
		j = estrategia.decideJogada(List.of(new Peca(0, 6), new Peca(1, 0), new Peca(6, 5), new Peca(0, 0)), mesa);
		assertEquals("0:0", j.getPeca().toString());
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
		
		// Joga primeira carroça possível
		j = estrategia.decideJogada(List.of(new Peca(5, 5), new Peca(5, 1), new Peca(6, 0), new Peca(0, 6)), mesa);
		assertEquals("6:0", j.getPeca().toString());
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
		
		// Joga primeira possível
		j = estrategia.decideJogada(List.of(new Peca(6, 6), new Peca(0, 3), new Peca(4, 5), new Peca(0, 6)), mesa);
		assertEquals("6:6", j.getPeca().toString());
		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
		
		// Joga primeira carroça possível
		j = estrategia.decideJogada(List.of(new Peca(5, 5), new Peca(5, 1), new Peca(5, 3), new Peca(3, 1)), mesa);
		assertEquals(TipoJogada.PASSA, j.getTipo());
	}
}
