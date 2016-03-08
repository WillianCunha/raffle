package com.softexpert.raffle;

import java.io.IOException;
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

	private FileReader fileReader = new FileReader();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	private static final List<String> DEFAULT_NAMES_LIST = Arrays.asList("ABNER MARCOS ORLAMUNDER", "ABRAAO PUZAK",
			"ADAEL OURIQUES DA CONCEICAO", "ADILSON JOAO REIS JUNIOR", "ADRIAN MATEUS SOUSA SANTOS",
			"ADRIANA ALVES DO PRADO");

	@Test
	public void oneWinnerDrawingStatic() {
		Mockito.when(randomGenerator.nextInt(6)).thenReturn(5, 0);
		Collection<String> winners = drawWinner.buildWinner(DEFAULT_NAMES_LIST, 2);
		MatcherAssert.assertThat(winners, Matchers.contains("ADRIANA ALVES DO PRADO", "ABNER MARCOS ORLAMUNDER"));
		Mockito.verify(randomGenerator, Mockito.times(2)).nextInt(Mockito.anyInt());
	}

	@Test
	public void fiveWinnersDrawingStatic() {
		Collection<String> winners = drawWinner.buildWinner(DEFAULT_NAMES_LIST, 5);
		MatcherAssert.assertThat(winners,
				Matchers.anyOf(Matchers.hasItem("ABNER MARCOS ORLAMUNDER"), Matchers.hasItem("ABRAAO PUZAK"),
						Matchers.hasItem("ADAEL OURIQUES DA CONCEICAO"), Matchers.hasItem("ADILSON JOAO REIS JUNIOR"),
						Matchers.hasItem("ADRIAN MATEUS SOUSA SANTOS"), Matchers.hasItem("ADRIANA ALVES DO PRADO")));
	}

	@Test
	public void fiveWinnersDrawingFromFile() throws IOException {
		List<String> nameList = fileReader.readFile("src/test/resources/com/softexpert/name-list-01.txt");
		List<String> winners = drawWinner.buildWinner(nameList, 5);

		MatcherAssert.assertThat(winners.get(0), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(1), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(2), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(3), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(4), Matchers.isIn(nameList));
	}

	@Test
	public void fiveWinnersDrawingTwo() throws IOException {
		List<String> nameList = fileReader.readFile("src/test/resources/com/softexpert/name-list-02.txt");
		List<String> winners = drawWinner.buildWinner(nameList, 5);

		MatcherAssert.assertThat(winners.get(0), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(1), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(2), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(3), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(4), Matchers.isIn(nameList));
	}

	@Test
	public void hundredWinnersDrawing() throws IOException {
		List<String> nameList = fileReader.readFile("src/test/resources/com/softexpert/name-list-02.txt");
		Collection<String> winners = drawWinner.buildWinner(nameList, 100);
		MatcherAssert.assertThat(winners, Matchers.hasSize(100));
	}

	@Test
	public void overThousandWinnersDrawing() throws IOException {
		List<String> nameList = fileReader.readFile("src/test/resources/com/softexpert/name-list-03.txt");
		Collection<String> winners = drawWinner.buildWinner(nameList, 1113);
		MatcherAssert.assertThat(winners, Matchers.hasSize(1113));

	}

}
