package com.softexpert.raffle;

import java.io.File;
import java.io.IOException;
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
import org.mockito.junit.MockitoRule;

public class IntegrationTest {

	@InjectMocks
	private DrawWinner drawWinner;
	@Mock
	private Random randomGenerator;

	private FileReader fileReader = new FileReader();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void fiveWinnersDrawingFromFile() throws IOException {
		Mockito.when(randomGenerator.nextInt(Mockito.anyInt())).thenReturn(0, 1, 2, 3, 4);
		File file = new File("src/test/resources/com/softexpert/name-list-01.txt");
		List<String> nameList = fileReader.readFile(file);
		List<String> winners = drawWinner.buildWinners(nameList, 5);

		MatcherAssert.assertThat(winners.get(0), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(1), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(2), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(3), Matchers.isIn(nameList));
		MatcherAssert.assertThat(winners.get(4), Matchers.isIn(nameList));
	}

	@Test
	public void fiveWinnersDrawingSize() throws IOException {
		Mockito.when(randomGenerator.nextInt(Mockito.anyInt())).thenReturn(0, 1, 2, 3, 4);
		File file = new File("src/test/resources/com/softexpert/name-list-02.txt");
		List<String> nameList = fileReader.readFile(file);
		Collection<String> winners = drawWinner.buildWinners(nameList, 5);

		MatcherAssert.assertThat(winners, Matchers.hasSize(5));
	}

}
