package com.softexpert.namegenerator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

import com.softexpert.exception.InvalidFileException;
import com.softexpert.exception.RaffleException;
import com.softexpert.exception.TooManyNamesException;
import com.softexpert.raffle.DrawWinner;

public class NameManager {

	private Random randomGenerator = new Random();
	private DrawWinner drawWinner = new DrawWinner(randomGenerator);

	public void saveNamesToFile(List<String> namesList, String filePath) throws RaffleException {
		try {
			PrintWriter writer = new PrintWriter(filePath, "UTF-8");
			for (String name : namesList) {
				writer.write(name);
				writer.write("\n");
			}
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException exception) {
			throw new InvalidFileException("Erro: Arquivo inválido ou inexistente.", exception);
		}
	}

	public List<String> extractFirstName(List<String> fullNamesList) {
		List<String> firstNamesList = new ArrayList<String>();
		for (String name : fullNamesList) {
			StringTokenizer stringTokenizer = new StringTokenizer(name);
			firstNamesList.add(stringTokenizer.nextToken());
		}
		return firstNamesList;
	}

	public List<String> extractLastNames(List<String> fullNamesList) {
		List<String> lastNamesList = new ArrayList<String>();
		for (String name : fullNamesList) {
			String[] fullNameSplit = name.split("\\s");
			lastNamesList.add(buildLastNames(fullNameSplit));
		}
		return lastNamesList;
	}

	public String buildLastNames(String[] fullNameSplit) {
		String lastNames = new String();
		for (int i = 1; i < fullNameSplit.length; i++) {
			if (i == (fullNameSplit.length - 1))
				lastNames = lastNames.concat(fullNameSplit[i]);
			else
				lastNames = lastNames.concat(fullNameSplit[i] + " ");
		}
		return lastNames;
	}

	public List<String> removeDuplicateNames(List<String> duplicateNamesList) {
		Set<String> uniqueNamesList = new HashSet<String>();
		for (String duplicate : duplicateNamesList) {
			uniqueNamesList.add(duplicate);
		}
		List<String> finalList = new ArrayList<String>(uniqueNamesList);
		Collections.sort(finalList);
		return finalList;
	}

	public List<String> buildUniqueFullNamesList(List<String> firstNamesList, List<String> lastNamesList,
			int namesListSize) throws RaffleException {

		if (namesListSize <= (firstNamesList.size() * lastNamesList.size())) {
			List<String> finalList = new ArrayList<String>(buildUniqueFullNames(firstNamesList, lastNamesList, namesListSize));
			Collections.sort(finalList);
			return finalList;
		} else {
			throw new TooManyNamesException("Erro: número de nomes únicos muito alto, máximo de "
					+ firstNamesList.size() * lastNamesList.size());
		}
	}

	public Set<String> buildUniqueFullNames(List<String> firstNamesList, List<String> lastNamesList,
			int namesListSize) throws RaffleException {
		String uniqueName = new String();
		Set<String> finalNamesSet = new HashSet<String>();
		while (finalNamesSet.size() != namesListSize) {
			List<String> firstName = drawWinner.buildWinners(firstNamesList, 1);
			List<String> lastName = drawWinner.buildWinners(lastNamesList, 1);
			uniqueName = firstName.get(0);
			uniqueName = uniqueName.concat(" " + lastName.get(0));
			finalNamesSet.add(uniqueName);
		}
		return finalNamesSet;
	}
}
