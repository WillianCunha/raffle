package com.softexpert.raffle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class FileReaderTest {
	private FileReader fileReader = new FileReader();

	@Test
	public void simpleFileReaderOne() throws IOException {
		List<String> nameList = new ArrayList<String>();
		nameList = fileReader.readFile("src/test/resources/com/softexpert/name-list-01.txt");
		MatcherAssert.assertThat(nameList,
				Matchers.contains("ABNER MARCOS ORLAMUNDER", "ABRAAO PUZAK", "ADAEL OURIQUES DA CONCEICAO",
						"ADILSON JOAO REIS JUNIOR", "ADRIAN MATEUS SOUSA SANTOS", "ADRIANA ALVES DO PRADO"));
	}

}
