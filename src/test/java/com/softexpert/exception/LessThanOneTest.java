package com.softexpert.exception;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

import com.softexpert.exception.LessThanOneException;
import com.softexpert.raffle.DrawWinner;

public class LessThanOneTest {

	private Random randomGenerator = new Random();
	private DrawWinner drawWinner = new DrawWinner(randomGenerator);

	private static final List<String> DEFAULT_NAMES_LIST = Arrays.asList("ABNER MARCOS ORLAMUNDER", "ABRAAO PUZAK",
			"ADAEL OURIQUES DA CONCEICAO", "ADILSON JOAO REIS JUNIOR", "ADRIAN MATEUS SOUSA SANTOS",
			"ADRIANA ALVES DO PRADO");


	@Test
	public void zeroWinnersTest() {
		try {
			int numberOfWinners = 0;
//			Mockito.when(randomGenerator.nextInt(Mockito.anyInt()));
			Collection<String> winners = drawWinner.buildWinners(DEFAULT_NAMES_LIST, numberOfWinners);
			MatcherAssert.assertThat(winners, Matchers.empty());
//			Mockito.verify(randomGenerator, Mockito.times(2)).nextInt(Mockito.anyInt());
		} catch (LessThanOneException exception) {
			exception.printZeroWinner();
		}
	}
	
	@Test
	public void negativeWinnersTest() {
		try {
			int numberOfWinners = -1;
			Collection<String> winners = drawWinner.buildWinners(DEFAULT_NAMES_LIST, numberOfWinners);
			MatcherAssert.assertThat(winners, Matchers.empty());
		} catch (LessThanOneException exception) {
			exception.printNegativeWinner();
		}
	}
}
