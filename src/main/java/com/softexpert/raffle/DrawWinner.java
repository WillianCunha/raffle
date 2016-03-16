package com.softexpert.raffle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.softexpert.exception.LessThanOneException;
import com.softexpert.exception.TooManyWinnersException;

public class DrawWinner {

	private Random randomGenerator;

	public DrawWinner(Random randomGenerator) {
		this.randomGenerator = randomGenerator;
	}

	public List<String> buildWinners(List<String> participants, int numberOfWinners)
			throws LessThanOneException, TooManyWinnersException {
		List<String> winners = new ArrayList<String>();
		List<String> list = new ArrayList<>(participants);
		if (numberOfWinners > list.size())
			throw new TooManyWinnersException("Erro: muitos ganhadores.");
		if (numberOfWinners >= 1) {
			for (int i = 0; i < numberOfWinners; i++) {
				String winnerCandidate = getRandomWinner(list);
				list = buildWithoutWinner(participants, winnerCandidate);
				winners.add(winnerCandidate);
			}
			return winners;
		}
		throw new LessThanOneException("Erro: valor de ganhadores menor que um.");
	}

	private List<String> buildWithoutWinner(List<String> participants, String winnerCandidate) {
		return participants.stream().filter(winner -> !winner.equals(winnerCandidate)).collect(Collectors.toList());
	}

	private String getRandomWinner(List<String> participants) {
		int position = randomGenerator.nextInt(participants.size());
		return participants.get(position);
	}

}
