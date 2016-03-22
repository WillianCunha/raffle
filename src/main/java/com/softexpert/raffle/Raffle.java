package com.softexpert.raffle;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.softexpert.exception.InvalidFileException;
import com.softexpert.exception.RaffleException;

public class Raffle {

	public List<String> readFile(File file) throws RaffleException {
		try {
			List<String> participants = Files.readLines(file, Charsets.UTF_8);
			checkForValidFile(participants);
			return participants;
		} catch (IOException exception) {
			throw new InvalidFileException("Erro: Arquivo inválido ou inexistente.", exception);
		}

	}

	private void checkForValidFile(List<String> participants) throws RaffleException {
		if (participants.isEmpty())
			throw new InvalidFileException("Erro: Arquivo inválido ou inexistente.");
	}
}
