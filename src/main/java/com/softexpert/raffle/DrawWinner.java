package com.softexpert.raffle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawWinner {

	private Random randomGenerator = new Random();

	public List<String> buildWinner(List<String> participants, int numberOfWinners) {
		List<String> winners = new ArrayList<String>();
		String winnerCandidate = "";

		while (winners.size() != numberOfWinners) {
			winnerCandidate = getRandomWinner(participants);
			boolean isOnTheList = false;
			for (String winner : winners)
				if (winner == winnerCandidate) {
					isOnTheList = true;
					break;
				}
			if (!isOnTheList)
				winners.add(winnerCandidate);
		}
		return winners;
	}

	private String getRandomWinner(List<String> participantes) {
		int randomNumber = randomGenerator.nextInt(participantes.size());
		return participantes.get(randomNumber);
	}

}
