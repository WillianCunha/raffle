package com.softexpert.raffle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.softexpert.exception.LessThanOneWinnerException;
import com.softexpert.exception.RaffleException;
import com.softexpert.exception.TooManyWinnersException;

public class DrawWinner {

	private Random randomGenerator;

	public DrawWinner(Random randomGenerator) {
		this.randomGenerator = randomGenerator;
	}

	public List<String> buildWinners(List<String> participants, int numberOfWinners) throws RaffleException {
		if (numberOfWinners >= 1)
			return checkIfTooManyWinners(participants, numberOfWinners);
		throw new LessThanOneWinnerException("Erro: valor de ganhadores menor que um.");
	}

	private List<String> checkIfTooManyWinners(List<String> participants, int numberOfWinners) throws RaffleException {
		if (numberOfWinners <= participants.size())
			return buildFinalWinnersList(participants, numberOfWinners);
		throw new TooManyWinnersException("Erro: muitos ganhadores: " + numberOfWinners
				+ "\nInforme um valor menor ou igual a " + participants.size() + " para o sorteio.");
	}

	private String getRandomWinner(List<String> participants) {
		int position = randomGenerator.nextInt(participants.size());
		return participants.get(position);
	}

	private List<String> buildFinalWinnersList(List<String> participants, int numberOfWinners) {
		List<String> winners = new ArrayList<String>();
		buildWithoutWinner(participants, winners, numberOfWinners);
		return winners;
	}
	
	private void buildWithoutWinner(List<String> participants, List<String> winners, int numberOfWinners) {
		List<String> mutableList = new ArrayList<String>(participants);
		for (int i = 0; i < numberOfWinners; i++) {
			removeWinner(mutableList, winners);
		}
	}
	
	private void removeWinner(List<String> participants, List<String> winners) {
		String winnerCandidate = getRandomWinner(participants);
		if (participants.removeIf(participant -> participant.equalsIgnoreCase(winnerCandidate)))
			winners.add(winnerCandidate);
	}
}
