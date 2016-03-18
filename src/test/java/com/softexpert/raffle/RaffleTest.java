package com.softexpert.raffle;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.softexpert.exception.RaffleException;

public class RaffleTest {

	@InjectMocks
	private Raffle raffle;
	@Mock
	private DrawWinner drawWinner;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void simpleFileReaderOne() throws RaffleException {
		Mockito.when(drawWinner.buildWinners(Arrays.asList("A", "B", "c", "W", "ã"), 3))
				.thenReturn(Arrays.asList("a", "c", "d"));
		List<String> candidates = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-01.txt"), 3);
		MatcherAssert.assertThat(candidates, Matchers.contains("a", "c", "d"));
		Mockito.verify(drawWinner).buildWinners(Arrays.asList("A", "B", "c", "W", "ã"), 3);
	}

	@Test(expected = RaffleException.class)
	public void emptyFileReader() throws RaffleException {
		raffle.readFile(new File("src/test/resources/com/softexpert/name-list-04.txt"), 1);
	}

	@Test(expected = RaffleException.class)
	public void noFile() throws RaffleException {
		raffle.readFile(new File("src/test/resources/com/softexpert/name-list-00.txt"), 1);
	}
}
