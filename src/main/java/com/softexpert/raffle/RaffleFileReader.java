package com.softexpert.raffle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RaffleFileReader {

	public List<String> readFile(String filename) throws IOException {
		InputStream inputStream = new FileInputStream(filename);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		return readLines(bufferedReader);
	}

	private List<String> readLines(BufferedReader bufferedReader) throws IOException {
		List<String> results = new ArrayList<String>();
		String value = "";
		while ((value = bufferedReader.readLine()) != null)
			results.add(value);
		return results;
	}

}
