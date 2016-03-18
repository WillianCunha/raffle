package com.softexpert.raffle;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.softexpert.exception.RaffleException;

public class RaffleIT {

	private Random random = new Random();
	private DrawWinner drawWinner = new DrawWinner(random);
	private Raffle raffle = new Raffle();

	@Test
	public void fiveWinnersDrawingFromFile() throws RaffleException, IOException {
		List<String> candidates = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-01.txt"), 5);
		List<String> winners = drawWinner.buildWinners(candidates, 5);
		MatcherAssert.assertThat(winners.get(0), Matchers.isIn(candidates));
		MatcherAssert.assertThat(winners.get(1), Matchers.isIn(candidates));
		MatcherAssert.assertThat(winners.get(2), Matchers.isIn(candidates));
		MatcherAssert.assertThat(winners.get(3), Matchers.isIn(candidates));
		MatcherAssert.assertThat(winners.get(4), Matchers.isIn(candidates));
	}

	@Test
	public void fiveWinnersDrawingSize() throws RaffleException, IOException {
		List<String> candidates = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-02.txt"), 5);
		Collection<String> winners = drawWinner.buildWinners(candidates, 5);
		MatcherAssert.assertThat(winners, Matchers.hasSize(5));
	}

	@Test
	public void hundredWinnersDrawingSize() throws RaffleException, IOException {
		List<String> candidates = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-02.txt"), 100);
		Collection<String> winners = drawWinner.buildWinners(candidates, 100);
		MatcherAssert.assertThat(winners, Matchers.hasSize(100));
	}

	@Test
	public void OverThousandDrawingSize() throws RaffleException, IOException {
		List<String> candidates = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-03.txt"), 1113);
		Collection<String> winners = drawWinner.buildWinners(candidates, 1113);
		MatcherAssert.assertThat(winners, Matchers.hasSize(1113));
	}
}