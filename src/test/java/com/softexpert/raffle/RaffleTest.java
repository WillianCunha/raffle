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

import com.softexpert.exception.EmptyFileException;
import com.softexpert.exception.MyException;

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
	public void simpleFileReaderOne() throws IOException, MyException {
		Mockito.when(drawWinner.buildWinners(Arrays.asList("A", "B", "c", "W", "ã"), 3))
				.thenReturn(Arrays.asList("a", "c", "d"));
		List<String> names = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-01.txt"), 3);
		MatcherAssert.assertThat(names, Matchers.contains("a", "c", "d"));
		Mockito.verify(drawWinner).buildWinners(Arrays.asList("A", "B", "c", "W", "ã"), 3);
	}

	@Test(expected = EmptyFileException.class)
	public void emptyFileReader() throws IOException, MyException {
		raffle.readFile(new File("src/test/resources/com/softexpert/name-list-04.txt"), 1);
	}

	@Test(expected = IOException.class)
	public void noFile() throws IOException, MyException {
		raffle.readFile(new File("src/test/resources/com/softexpert/name-list-00.txt"), 1);
	}
}
