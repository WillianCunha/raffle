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
			return buildWithoutWinner(participants, numberOfWinners);
		throw new TooManyWinnersException("Erro: muitos ganhadores: " + numberOfWinners
				+ "\nInforme um valor menor ou igual a " + participants.size() + " para o sorteio.");
	}

	private String getRandomWinner(List<String> participants) {
		int position = randomGenerator.nextInt(participants.size());
		return participants.get(position);
	}

	
	private List<String> buildWithoutWinner(List<String> participants, int numberOfWinners) {
		List<String> eligiblesForDrawing = new ArrayList<String>(participants);
		List<String> winners = new ArrayList<String>();
		for (int i = 0; i < numberOfWinners; i++) 
			removeAndDrawWinner(eligiblesForDrawing, winners);
		return winners;
	}
	
	private void removeAndDrawWinner(List<String> participants, List<String> winners) {
		String winnerCandidate = getRandomWinner(participants);
		if (participants.removeIf(participant -> participant.equalsIgnoreCase(winnerCandidate)))
			winners.add(winnerCandidate);
	}
}
