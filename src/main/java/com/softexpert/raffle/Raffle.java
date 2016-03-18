package com.softexpert.raffle;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.softexpert.exception.RaffleException;

public class Raffle {

	private DrawWinner drawWinner;

	public Raffle() {
		drawWinner = new DrawWinner(new Random());
	}

	public Raffle(DrawWinner drawWinner) {
		this.drawWinner = drawWinner;
	}

	public List<String> readFile(File file, int numberOfWinners) throws IOException, RaffleException {
		List<String> participants = Files.readLines(file, Charsets.UTF_8);
		checkForValidFile(participants);
		
		return drawWinner.buildWinners(participants, numberOfWinners);
	}

	private void checkForValidFile(List<String> participants) throws IOException {
		if (participants.isEmpty())
			throw new IOException("Erro: Problema de leitura ou arquivo vazio.");
	}
}
