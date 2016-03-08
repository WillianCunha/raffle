package com.softexpert.raffle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawWinner {

	private Random randomGenerator;

	public DrawWinner(Random randomGenerator) {
		this.randomGenerator = randomGenerator;
	}

	public List<String> buildWinners(List<String> participants, int numberOfWinners) {
		List<String> winners = new ArrayList<String>();
		for (int i = 0; i < numberOfWinners; i++)
			buildWinner(participants, winners);
		return winners;
	}

	private void buildWinner(List<String> participants, List<String> winners) {
		String winnerCandidate = getRandomWinner(participants);
		if (isUnique(winners, winnerCandidate))
			winners.add(winnerCandidate);
	}

	private String getRandomWinner(List<String> participants) {
		int randomNumber = randomGenerator.nextInt(participants.size());
		return participants.get(randomNumber);
	}

	private boolean isUnique(List<String> winners, String winnerCanditate) {
		return !winners.contains(winnerCanditate);
	}

}
