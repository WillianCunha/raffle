package com.softexpert.raffle;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ExtractFirstNameTest {
	private ExtractFirstName extractFirstName = new ExtractFirstName();
	
	@Test
	public void firstNameExtractor() {
		List<String> nameList = Arrays.asList("ABNER MARCOS ORLAMUNDER", "ABRAAO PUZAK", "ADAEL OURIQUES DA CONCEICAO",
						"ADILSON JOAO REIS JUNIOR", "ADRIAN MATEUS SOUSA SANTOS", "ADRIANA ALVES DO PRADO");
		Set<String> firstNameList = extractFirstName.firstNameExtractor(nameList);
	}
}
