package com.softexpert.raffle;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.softexpert.exception.RaffleException;
import com.softexpert.exception.TooManyNamesException;
import com.softexpert.namegenerator.NameManager;

public class NameManagerTest {

	private static final List<String> DEFAULT_NAMES_LIST = Arrays.asList("ABNER MARCOS ORLAMUNDER", "ABRAAO PUZAK",
			"ADAEL OURIQUES DA CONCEICAO", "ADILSON JOAO REIS JUNIOR", "ADRIAN MATEUS SOUSA SANTOS",
			"ADRIANA ALVES DO PRADO");
	private static final List<String> DUPLICATE_NAMES_LIST = Arrays.asList("JOAO", "LUCAS", "JOAO", "ANA", "ANA",
			"MATEUS", "MATEUS");
	private NameManager nameManager = new NameManager();

	@Test
	public void testSingleFirstNameExtraction() {
		List<String> onePersonList = Arrays.asList("ABNER MARCOS ORLAMUNDER");
		List<String> firstNameList = nameManager.extractFirstName(onePersonList);
		MatcherAssert.assertThat(firstNameList, Matchers.contains("ABNER"));
	}

	@Test
	public void testMultipleNamesExtraction() {
		List<String> firstNamesList = nameManager.extractFirstName(DEFAULT_NAMES_LIST);
		MatcherAssert.assertThat(firstNamesList,
				Matchers.contains("ABNER", "ABRAAO", "ADAEL", "ADILSON", "ADRIAN", "ADRIANA"));
	}

	@Test
	public void testDuplicateNamesRemoval() {
		List<String> uniqueNames = nameManager.removeDuplicateNames(DUPLICATE_NAMES_LIST);
		MatcherAssert.assertThat(uniqueNames, Matchers.hasSize(4));
		MatcherAssert.assertThat(uniqueNames, Matchers.containsInAnyOrder("JOAO", "LUCAS", "ANA", "MATEUS"));
	}

	@Test
	public void testSingleLastNamesExtraction() {
		List<String> onePersonList = Arrays.asList("ABNER MARCOS ORLAMUNDER");
		List<String> lastNamesList = nameManager.extractLastNames(onePersonList);
		MatcherAssert.assertThat(lastNamesList, Matchers.contains("MARCOS ORLAMUNDER"));
	}

	@Test
	public void testThreeNameCombination() throws RaffleException {
		List<String> firstNamesList = nameManager.extractFirstName(
				Arrays.asList("ABNER MARCOS ORLAMUNDER", "ABRAAO PUZAK", "ADAEL OURIQUES DA CONCEICAO"));
		List<String> lastNamesList = nameManager.extractLastNames(
				Arrays.asList("ABNER MARCOS ORLAMUNDER", "ABRAAO PUZAK", "ADAEL OURIQUES DA CONCEICAO"));
		List<String> createdNames = nameManager.buildUniqueFullNamesList(firstNamesList, lastNamesList, firstNamesList.size() * lastNamesList.size());
		MatcherAssert.assertThat(createdNames, Matchers.hasSize(firstNamesList.size() * lastNamesList.size()));
	}

	@Test
	public void testSixNameCombination() throws RaffleException {
		List<String> firstNamesList = nameManager.extractFirstName(DEFAULT_NAMES_LIST);
		List<String> lastNamesList = nameManager.extractLastNames(DEFAULT_NAMES_LIST);
		List<String> createdNames = nameManager.buildUniqueFullNamesList(firstNamesList, lastNamesList,
				firstNamesList.size() * lastNamesList.size());
		MatcherAssert.assertThat(createdNames, Matchers.hasSize(firstNamesList.size() * lastNamesList.size()));
	}
	
	@Test(expected = TooManyNamesException.class)
	public void testImpossibleNameCombination() throws RaffleException {
		List<String> firstNamesList = nameManager.extractFirstName(DEFAULT_NAMES_LIST);
		List<String> lastNamesList = nameManager.extractLastNames(DEFAULT_NAMES_LIST);
		List<String> createdNames = nameManager.buildUniqueFullNamesList(firstNamesList, lastNamesList, 100);
	}
}
