package com.softexpert.raffle;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.softexpert.exception.InvalidFileException;
import com.softexpert.exception.RaffleException;

public class Raffle {

	private DrawWinner drawWinner;

	public Raffle() {
		drawWinner = new DrawWinner(new Random());
	}

	public Raffle(DrawWinner drawWinner) {
		this.drawWinner = drawWinner;
	}

	public List<String> readFile(File file, int numberOfWinners) throws RaffleException {
		List<String> participants;
		try {
			participants = Files.readLines(file, Charsets.UTF_8);
		} catch (IOException exception) {
			throw new InvalidFileException("Erro: Arquivo inválido ou inexistente.", exception);
		}
		checkForValidFile(participants);
		
		return drawWinner.buildWinners(participants, numberOfWinners);
	}

	private void checkForValidFile(List<String> participants) throws RaffleException {
		if (participants.isEmpty())
			throw new InvalidFileException("Erro: Arquivo inválido ou inexistente.");
	}
}
