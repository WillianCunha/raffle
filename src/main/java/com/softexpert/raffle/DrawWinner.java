package com.softexpert.raffle;

import java.util.List;
import java.util.Random;

public class DrawWinner {
	private Random randomGenerator = new Random();

	public String selectWinner(List<String> nameList, int seed) {
		if (seed != 0) {
			randomGenerator.setSeed(seed);
			return nameList.get(randomGenerator.nextInt(nameList.size() - 1));
		} else
			return nameList.get(randomGenerator.nextInt(nameList.size() - 1));
	}

}
