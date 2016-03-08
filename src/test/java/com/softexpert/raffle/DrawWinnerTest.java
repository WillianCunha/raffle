package com.softexpert.raffle;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class DrawWinnerTest {

	@InjectMocks
	private DrawWinner drawWinner;
	@Mock
	private Random randomGenerator;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	private static final List<String> DEFAULT_NAMES_LIST = Arrays.asList("ABNER MARCOS ORLAMUNDER", "ABRAAO PUZAK",
			"ADAEL OURIQUES DA CONCEICAO", "ADILSON JOAO REIS JUNIOR", "ADRIAN MATEUS SOUSA SANTOS",
			"ADRIANA ALVES DO PRADO");

	@Test
	public void twoWinnerDrawingStatic() {
		Mockito.when(randomGenerator.nextInt(6)).thenReturn(0, 5);
		Collection<String> winners = drawWinner.buildWinners(DEFAULT_NAMES_LIST, 2);
		MatcherAssert.assertThat(winners, Matchers.contains("ABNER MARCOS ORLAMUNDER", "ADRIANA ALVES DO PRADO"));
		Mockito.verify(randomGenerator, Mockito.times(2)).nextInt(Mockito.anyInt());
	}

	@Test
	public void sixWinnersDrawingStatic() {
		Mockito.when(randomGenerator.nextInt(6)).thenReturn(0, 1, 2, 3, 4, 5);
		Collection<String> winners = drawWinner.buildWinners(DEFAULT_NAMES_LIST, 6);
		MatcherAssert.assertThat(winners,
				Matchers.anyOf(Matchers.hasItem("ABNER MARCOS ORLAMUNDER"), Matchers.hasItem("ABRAAO PUZAK"),
						Matchers.hasItem("ADAEL OURIQUES DA CONCEICAO"), Matchers.hasItem("ADILSON JOAO REIS JUNIOR"),
						Matchers.hasItem("ADRIAN MATEUS SOUSA SANTOS"), Matchers.hasItem("ADRIANA ALVES DO PRADO")));
		Mockito.verify(randomGenerator, Mockito.times(6)).nextInt(Mockito.anyInt());
	}

}
