package com.softexpert.raffle;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class Raffle {
	
	private DrawWinner drawWinner;
	
	public Raffle() {
		 drawWinner = new DrawWinner(new Random());
	}
	
	public Raffle(DrawWinner drawWinner) {
		 this.drawWinner = drawWinner;
	}
	
	public List<String> readFile(File file, int numberOfWinners) throws IOException {
		List<String> participants = Files.readLines(file, Charsets.UTF_8);
		return drawWinner.buildWinners(participants, numberOfWinners);
	}
}
