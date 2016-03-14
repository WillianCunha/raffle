package com.softexpert.raffle;

import java.io.File;
import java.io.IOException;
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
	public void simpleFileReaderOne() throws IOException {
		Mockito.when(drawWinner.buildWinners(Arrays.asList("A", "B", "c", "W", "ã"), 3))
				.thenReturn(Arrays.asList("a", "c", "d"));
		List<String> names = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-01.txt"), 3);
		System.out.println(names);
		MatcherAssert.assertThat(names, Matchers.contains("a", "c", "d"));
		Mockito.verify(drawWinner).buildWinners(Arrays.asList("A", "B", "c", "W", "ã"), 3);
	}
}
