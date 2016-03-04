package com.softexpert.raffle;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class DrawWinnerTest {
	private DrawWinner drawWinner = new DrawWinner();

	@Test
	public void simpleWinnerDrawingOne() {
		String winner = new String();
		List<String> nameList = Arrays.asList("ABNER MARCOS ORLAMUNDER", "ABRAAO PUZAK", "ADAEL OURIQUES DA CONCEICAO",
				"ADILSON JOAO REIS JUNIOR", "ADRIAN MATEUS SOUSA SANTOS", "ADRIANA ALVES DO PRADO");

		winner = drawWinner.selectWinner(nameList, 0);
		System.out.println(winner);
		MatcherAssert.assertThat(winner,
				Matchers.isOneOf("ABNER MARCOS ORLAMUNDER", "ABRAAO PUZAK", "ADAEL OURIQUES DA CONCEICAO",
						"ADILSON JOAO REIS JUNIOR", "ADRIAN MATEUS SOUSA SANTOS", "ADRIANA ALVES DO PRADO"));
	}

}
