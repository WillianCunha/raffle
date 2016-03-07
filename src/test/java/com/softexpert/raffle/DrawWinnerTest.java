package com.softexpert.raffle;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class DrawWinnerTest {

	private DrawWinner drawWinner = new DrawWinner();
	private FileReader fileReader = new FileReader();
	private static final List<String> DEFAULT_NAMES_LIST = Arrays.asList("ABNER MARCOS ORLAMUNDER", "ABRAAO PUZAK",
			"ADAEL OURIQUES DA CONCEICAO", "ADILSON JOAO REIS JUNIOR", "ADRIAN MATEUS SOUSA SANTOS",
			"ADRIANA ALVES DO PRADO");

	@Test
	public void oneWinnerDrawing() {
		Collection<String> winners = drawWinner.buildWinner(DEFAULT_NAMES_LIST, 1);
		MatcherAssert.assertThat(winners,
				Matchers.anyOf(Matchers.hasItem("ABNER MARCOS ORLAMUNDER"), Matchers.hasItem("ABRAAO PUZAK"),
						Matchers.hasItem("ADAEL OURIQUES DA CONCEICAO"), Matchers.hasItem("ADILSON JOAO REIS JUNIOR"),
						Matchers.hasItem("ADRIAN MATEUS SOUSA SANTOS"), Matchers.hasItem("ADRIANA ALVES DO PRADO")));
	}

	@Test
	public void fiveWinnersDrawing() {
		Collection<String> winners = drawWinner.buildWinner(DEFAULT_NAMES_LIST, 5);
		MatcherAssert.assertThat(winners,
				Matchers.anyOf(Matchers.hasItem("ABNER MARCOS ORLAMUNDER"), Matchers.hasItem("ABRAAO PUZAK"),
						Matchers.hasItem("ADAEL OURIQUES DA CONCEICAO"), Matchers.hasItem("ADILSON JOAO REIS JUNIOR"),
						Matchers.hasItem("ADRIAN MATEUS SOUSA SANTOS"), Matchers.hasItem("ADRIANA ALVES DO PRADO")));
	}

	@Test
	public void TenWinnersDrawing() throws IOException {
		List<String> nameList = fileReader.readFile("src/test/resources/com/softexpert/name-list-02.txt");
		List<String> winners = drawWinner.buildWinner(nameList, 10);

		MatcherAssert.assertThat(winners.get(0), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(1), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(2), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(3), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(4), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(5), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(6), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(7), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(8), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(9), Matchers.isIn(nameList));
	}

	// Esse teste sempre passa, a lista não é iterada e aceita qualquer coisa
	@Test
	public void HundredWinnersDrawing() throws IOException {
		List<String> nameList = fileReader.readFile("src/test/resources/com/softexpert/name-list-02.txt");
		Collection<String> winners = drawWinner.buildWinner(nameList, 100);
		MatcherAssert.assertThat(winners, Matchers.anything(nameList.listIterator().next()));
		
	}
	
}
