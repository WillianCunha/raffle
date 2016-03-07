package com.softexpert.raffle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawWinner {
	
	private Random randomGenerator = new Random();

	public List<String> buildWinner(List<String> participants, int numberOfWinners) {
		List<String> winners = new ArrayList<String>();
		while (winners.size() != numberOfWinners)
			winners.add(getRandomWinner(participants));
		return winners;
	}

	private String getRandomWinner(List<String> participantes) {
		int randomNumber = randomGenerator.nextInt(participantes.size());
		return participantes.get(randomNumber);
	}

}
