package com.softexpert.raffle;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.softexpert.exception.RaffleException;

public class RaffleIT {

	private static final int FINAL_LIST_SIZE = 10000;
	private Raffle raffle = new Raffle();

	@Test
	public void fiveWinnersDrawingFromFile() throws RaffleException, IOException {
		List<String> winners = raffle.processFile(new File("src/test/resources/com/softexpert/name-list-01.txt"), 5);
		MatcherAssert.assertThat(winners, Matchers.containsInAnyOrder("A", "B", "c", "W", "ã"));
	}

	@Test
	public void fiveWinnersDrawingSize() throws RaffleException {
		List<String> winners = raffle.processFile(new File("src/test/resources/com/softexpert/name-list-02.txt"), 5);
		MatcherAssert.assertThat(winners, Matchers.hasSize(5));
	}

	@Test
	public void hundredWinnersDrawingSize() throws RaffleException {
		List<String> winners = raffle.processFile(new File("src/test/resources/com/softexpert/name-list-02.txt"), 100);
		MatcherAssert.assertThat(winners, Matchers.hasSize(100));
	}

	@Test
	public void overThousandWinnersDrawingSize() throws RaffleException {
		List<String> winners = raffle.processFile(new File("src/test/resources/com/softexpert/name-list-03.txt"), 1113);
		MatcherAssert.assertThat(winners, Matchers.hasSize(1113));
	}

	@Test
	public void tenThousandWinnersDrawingSize() throws RaffleException {
		double startTime = System.currentTimeMillis();
		List<String> winners = raffle.processFile(new File("src/test/resources/com/softexpert/name-list-final.txt"), FINAL_LIST_SIZE);
		double stopTime = System.currentTimeMillis();
		MatcherAssert.assertThat(winners, Matchers.hasSize(FINAL_LIST_SIZE));
		MatcherAssert.assertThat((stopTime - startTime) / 1000, Matchers.lessThan(3.0));
	}
}