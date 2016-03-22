package com.softexpert.raffle;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.rules.Stopwatch;

import com.softexpert.exception.InvalidFileException;
import com.softexpert.exception.RaffleException;
import com.softexpert.namegenerator.NameManager;

public class RaffleIT {

	private static final int FINAL_LIST_SIZE = 10000;
	private static final List<String> DEFAULT_NAMES_LIST = Arrays.asList("ABNER MARCOS ORLAMUNDER", "ABRAAO PUZAK",
			"ADAEL OURIQUES DA CONCEICAO", "ADILSON JOAO REIS JUNIOR", "ADRIAN MATEUS SOUSA SANTOS",
			"ADRIANA ALVES DO PRADO");
	private Random random = new Random();
	private DrawWinner drawWinner = new DrawWinner(random);
	private Raffle raffle = new Raffle();
	private NameManager nameManager = new NameManager();

	@Test
	public void fiveWinnersDrawingFromFile() throws RaffleException, IOException {
		List<String> candidates = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-01.txt"));
		List<String> winners = drawWinner.buildWinners(candidates, 5);
		MatcherAssert.assertThat(winners.get(0), Matchers.isIn(candidates));
		MatcherAssert.assertThat(winners.get(1), Matchers.isIn(candidates));
		MatcherAssert.assertThat(winners.get(2), Matchers.isIn(candidates));
		MatcherAssert.assertThat(winners.get(3), Matchers.isIn(candidates));
		MatcherAssert.assertThat(winners.get(4), Matchers.isIn(candidates));
	}

	@Test
	public void fiveWinnersDrawingSize() throws RaffleException {
		List<String> candidates = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-02.txt"));
		Collection<String> winners = drawWinner.buildWinners(candidates, 5);
		MatcherAssert.assertThat(winners, Matchers.hasSize(5));
	}

	@Test
	public void hundredWinnersDrawingSize() throws RaffleException {
		List<String> candidates = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-02.txt"));
		Collection<String> winners = drawWinner.buildWinners(candidates, 100);
		MatcherAssert.assertThat(winners, Matchers.hasSize(100));
	}

	@Test
	public void overThousandWinnersDrawingSize() throws RaffleException {
		List<String> candidates = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-03.txt"));
		Collection<String> winners = drawWinner.buildWinners(candidates, 1113);
		MatcherAssert.assertThat(winners, Matchers.hasSize(1113));
	}

	@Test
	public void testSaveFullNamesToFile() throws RaffleException {
		nameManager.saveNamesToFile(DEFAULT_NAMES_LIST, "src/test/resources/com/softexpert/name-list-temp.txt");
		List<String> names = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-temp.txt"));
		MatcherAssert.assertThat(names, Matchers.hasSize(6));
	}

	@Test
	public void testSaveFirstNamesToFile() throws RaffleException {
		List<String> firstNamesList = nameManager.extractFirstName(DEFAULT_NAMES_LIST);
		nameManager.saveNamesToFile(firstNamesList, "src/test/resources/com/softexpert/name-list-temp2.txt");
		List<String> firstNames = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-temp2.txt"));
		MatcherAssert.assertThat(firstNames,
				Matchers.contains("ABNER", "ABRAAO", "ADAEL", "ADILSON", "ADRIAN", "ADRIANA"));
	}

	@Test
	public void testExtractFirstNamesAndSaveToFile() throws RaffleException {
		List<String> names = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-03.txt"));
		List<String> firstNamesList = nameManager.extractFirstName(names);
		nameManager.saveNamesToFile(firstNamesList, "src/test/resources/com/softexpert/name-list-temp.txt");
		MatcherAssert.assertThat(firstNamesList, Matchers.hasSize(1313));
	}

	@Test
	public void testSaveLastNamesToFile() throws RaffleException {
		List<String> lastNamesList = nameManager.extractLastNames(DEFAULT_NAMES_LIST);
		nameManager.saveNamesToFile(lastNamesList, "src/test/resources/com/softexpert/name-list-temp3.txt");
		List<String> lastNames = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-temp3.txt"));
		MatcherAssert.assertThat(lastNames, Matchers.contains("MARCOS ORLAMUNDER", "PUZAK", "OURIQUES DA CONCEICAO",
				"JOAO REIS JUNIOR", "MATEUS SOUSA SANTOS", "ALVES DO PRADO"));
	}

	@Test
	public void testExtractLastNamesAndSaveToFile() throws RaffleException {
		List<String> names = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-03.txt"));
		List<String> lastNamesList = nameManager.extractLastNames(names);
		nameManager.saveNamesToFile(lastNamesList, "src/test/resources/com/softexpert/name-list-temp4.txt");
		MatcherAssert.assertThat(lastNamesList, Matchers.hasSize(1313));
	}

	@Test
	public void testRemoveDuplicateFirstNames() throws RaffleException {
		List<String> names = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-05.txt"));
		List<String> firstNamesList = nameManager.extractFirstName(names);
		List<String> uniqueNamesList = nameManager.removeDuplicateNames(firstNamesList);
		MatcherAssert.assertThat(uniqueNamesList, Matchers.hasSize(6));
		MatcherAssert.assertThat(uniqueNamesList,
				Matchers.contains("ALCEU", "AMANDA", "ANA", "JOAO", "MATEUS", "PEDRO"));
	}

	@Test
	public void testRemoveDuplicateLastNames() throws RaffleException {
		List<String> names = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-05.txt"));
		List<String> lastNamesList = nameManager.extractLastNames(names);
		List<String> uniqueNamesList = nameManager.removeDuplicateNames(lastNamesList);
		MatcherAssert.assertThat(uniqueNamesList, Matchers.hasSize(6));
		MatcherAssert.assertThat(uniqueNamesList,
				Matchers.contains("DA CUNHA", "DA ROSA", "DA SILVA", "PEREIRA", "PEREIRA DE OLIVEIRA", "SOUZA"));
	}
	
	@Test
	public void testGenerateBaseListOfFirstNames() throws RaffleException {
		List<String> names = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-03.txt"));
		List<String> firstNamesList = nameManager.extractFirstName(names);
		List<String> uniqueNamesList = nameManager.removeDuplicateNames(firstNamesList);
		nameManager.saveNamesToFile(uniqueNamesList, "src/test/resources/com/softexpert/name-list-firstnames.txt");
		MatcherAssert.assertThat(uniqueNamesList, Matchers.hasSize(668));
	}
	
	@Test
	public void testGenerateBaseListOfLastNames() throws RaffleException {
		List<String> names = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-03.txt"));
		List<String> lastNamesList = nameManager.extractLastNames(names);
		List<String> uniqueNamesList = nameManager.removeDuplicateNames(lastNamesList);
		nameManager.saveNamesToFile(uniqueNamesList, "src/test/resources/com/softexpert/name-list-lastnames.txt");
		MatcherAssert.assertThat(uniqueNamesList, Matchers.hasSize(1257));
	}
	
	@Test
	public void testGenerateFinalListOfNames() throws RaffleException {
		List<String> firstNamesList = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-firstnames.txt"));
		List<String> lastNamesList = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-lastnames.txt"));
		List<String> createdNames = nameManager.buildUniqueFullNamesList(firstNamesList, lastNamesList, FINAL_LIST_SIZE);
		nameManager.saveNamesToFile(createdNames, "src/test/resources/com/softexpert/name-list-final.txt");
		MatcherAssert.assertThat(createdNames, Matchers.hasSize(FINAL_LIST_SIZE));
	}
	
	@Test
	public void tenThousandWinnersDrawingSize() throws RaffleException {
		double startTime = System.currentTimeMillis();
		List<String> nameList = raffle.readFile(new File("src/test/resources/com/softexpert/name-list-final.txt"));
		List<String> winnersList = drawWinner.buildWinners(nameList, FINAL_LIST_SIZE);
		MatcherAssert.assertThat(winnersList, Matchers.hasSize(FINAL_LIST_SIZE));
		double stopTime = System.currentTimeMillis();
		System.out.println("Tempo de execução: " + ((stopTime - startTime) / 1000) + " segundos");
	}

	@Test(expected = InvalidFileException.class)
	public void testInvalidFile() throws RaffleException {
		nameManager.saveNamesToFile(DEFAULT_NAMES_LIST, "z://teste");
	}
}