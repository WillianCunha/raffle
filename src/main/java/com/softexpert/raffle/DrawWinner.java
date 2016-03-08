package com.softexpert.raffle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawWinner {

	private Random randomGenerator;
	
	public DrawWinner(Random randomGenerator){
		this.randomGenerator = randomGenerator;
	}

	public List<String> buildWinner(List<String> participants, int numberOfWinners) {
		List<String> winners = new ArrayList<String>();
		String winnerCandidate = "";

		while (winners.size() != numberOfWinners) {
			winnerCandidate = getRandomWinner(participants);
			if (isUnique(winners, winnerCandidate))
				winners.add(winnerCandidate);
		}
		return winners;
	}

	private String getRandomWinner(List<String> participants) {
		int randomNumber = randomGenerator.nextInt(participants.size());
		return participants.get(randomNumber);
	}
	
	private boolean isUnique(List<String> winners, String winnerCanditate) {
		boolean isUnique = true;
		
		for (String winner : winners)
			if (winner == winnerCanditate) {
				isUnique = false;
				break;
			}
		return isUnique;
	}

}
