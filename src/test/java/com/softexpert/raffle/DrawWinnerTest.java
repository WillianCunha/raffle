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

import com.softexpert.exception.LessThanOneException;
import com.softexpert.exception.RaffleException;
import com.softexpert.exception.TooManyWinnersException;

public class DrawWinnerTest {

	private static final List<String> DEFAULT_NAMES_LIST = Arrays.asList("ABNER MARCOS ORLAMUNDER", "ABRAAO PUZAK",
			"ADAEL OURIQUES DA CONCEICAO", "ADILSON JOAO REIS JUNIOR", "ADRIAN MATEUS SOUSA SANTOS",
			"ADRIANA ALVES DO PRADO");

	@InjectMocks
	private DrawWinner drawWinner;
	@Mock
	private Random randomGenerator;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void twoWinnerDrawingStatic() throws RaffleException {
		Mockito.when(randomGenerator.nextInt(Mockito.anyInt())).thenReturn(0, 4);
		Collection<String> winners = drawWinner.buildWinners(DEFAULT_NAMES_LIST, 2);
		MatcherAssert.assertThat(winners, Matchers.contains("ABNER MARCOS ORLAMUNDER", "ADRIANA ALVES DO PRADO"));
		Mockito.verify(randomGenerator, Mockito.times(2)).nextInt(Mockito.anyInt());
	}

	@Test
	public void sixWinnersDrawingStatic() throws RaffleException {
		Mockito.when(randomGenerator.nextInt(Mockito.anyInt())).thenReturn(0, 0, 0, 0, 0, 0);
		Collection<String> winners = drawWinner.buildWinners(DEFAULT_NAMES_LIST, 6);
		MatcherAssert.assertThat(winners,
				Matchers.anyOf(Matchers.hasItem("ABNER MARCOS ORLAMUNDER"), Matchers.hasItem("ABRAAO PUZAK"),
						Matchers.hasItem("ADAEL OURIQUES DA CONCEICAO"), Matchers.hasItem("ADILSON JOAO REIS JUNIOR"),
						Matchers.hasItem("ADRIAN MATEUS SOUSA SANTOS"), Matchers.hasItem("ADRIANA ALVES DO PRADO")));
		Mockito.verify(randomGenerator, Mockito.times(6)).nextInt(Mockito.anyInt());
	}

	@Test
	public void duplicateWinnerDrawing() throws RaffleException {
		Mockito.when(randomGenerator.nextInt(Mockito.anyInt())).thenReturn(1, 1);
		Collection<String> winners = drawWinner.buildWinners(DEFAULT_NAMES_LIST, 2);
		MatcherAssert.assertThat(winners, Matchers.hasSize(2));
		MatcherAssert.assertThat(winners, Matchers.contains("ABRAAO PUZAK", "ADAEL OURIQUES DA CONCEICAO"));
		Mockito.verify(randomGenerator, Mockito.times(2)).nextInt(Mockito.anyInt());
	}

	@Test(expected = LessThanOneException.class)
	public void zeroWinnerTest() throws RaffleException {
		int numberOfWinners = 0;
		Collection<String> winners = drawWinner.buildWinners(DEFAULT_NAMES_LIST, numberOfWinners);
		MatcherAssert.assertThat(winners, Matchers.empty());
	}

	@Test(expected = LessThanOneException.class)
	public void negativeWinnersTest() throws RaffleException {
		int numberOfWinners = -1;
		Collection<String> winners = drawWinner.buildWinners(DEFAULT_NAMES_LIST, numberOfWinners);
		MatcherAssert.assertThat(winners, Matchers.empty());
	}

	@Test(expected = TooManyWinnersException.class)
	public void tooManyWinnersTest() throws RaffleException {
		int numberOfWinners = 10;
		Collection<String> winners = drawWinner.buildWinners(DEFAULT_NAMES_LIST, numberOfWinners);
		MatcherAssert.assertThat(winners, Matchers.empty());
	}

}
