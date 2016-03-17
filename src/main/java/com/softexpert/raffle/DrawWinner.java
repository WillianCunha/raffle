package com.softexpert.raffle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.softexpert.exception.InsufficientWinnersException;
import com.softexpert.exception.LessThanOneException;
import com.softexpert.exception.RaffleException;
import com.softexpert.exception.TooManyWinnersException;

public class DrawWinner {

	private Random randomGenerator;

	public DrawWinner(Random randomGenerator) {
		this.randomGenerator = randomGenerator;
	}

	public List<String> buildWinners(List<String> participants, int numberOfWinners) throws RaffleException {
		if (numberOfWinners >= 1) {
			List<String> winners = checkIfTooManyWinners(participants, numberOfWinners);
			return winners;
		}
		throw new LessThanOneException("Erro: valor de ganhadores menor que um.");
	}

	private List<String> checkIfTooManyWinners(List<String> participants, int numberOfWinners) throws RaffleException {
		List<String> winners = new ArrayList<String>();
		int winnersCount = 0, attempts = 0;
		if (numberOfWinners <= participants.size()) {
			while (winnersCount != numberOfWinners && attempts != 20000) {
				String winnerCandidate = getRandomWinner(participants);
				winnersCount = buildExclusiveWinner(winnersCount, winnerCandidate, winners);
				attempts++;
			}
			return winners;
			// Queria usar a InsufficientWinnersException,
			// mas o código não compila com essa chamada de método
			// checkForLooping(numberOfWinners, winnersCount);
		} else
			throw new TooManyWinnersException("Erro: muitos ganhadores: " + numberOfWinners
					+ "\nInforme um valor menor ou igual a " + participants.size() + " para o sorteio.");
	}

	private String getRandomWinner(List<String> participants) {
		int position = randomGenerator.nextInt(participants.size());
		return participants.get(position);
	}

	// Não entendi muito bem o funcionamento desse método
	private List<String> buildWithoutWinner(List<String> participants, String winnerCandidate) {
		return participants.stream().filter(winner -> !winner.equalsIgnoreCase(winnerCandidate))
				.collect(Collectors.toList());
	}

	private int buildExclusiveWinner(int winnersCount, String winnerCandidate, List<String> winners) {
		boolean isExclusive = true;
		for (int i = 0; i < winners.size(); i++) {
			if (winnerCandidate.equalsIgnoreCase(winners.get(i))) {
				isExclusive = false;
				break;
			}
		}
		if (isExclusive) {
			winners.add(winnerCandidate);
			winnersCount++;
		}
		return winnersCount;
	}

	private void checkForLooping(int numberOfWinners, int winnersCount) throws RaffleException {
		if (winnersCount < numberOfWinners)
			throw new InsufficientWinnersException(
					"Erro: número de vencedores não atingido.\nProvável tentativa duplicada de vencedores.");
	}
}
