package com.softexpert.raffle;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class FileReader {

	public List<String> readFile(File file) throws IOException {
		return Files.readLines(file, Charsets.UTF_8);
	}

}
