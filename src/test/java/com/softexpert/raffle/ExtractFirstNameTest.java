package com.softexpert.raffle;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class ExtractFirstNameTest {
	private ExtractFirstName extractFirstName = new ExtractFirstName();
	
	@Test
	public void singleNameExtraction() {
		String name = "ABNER MARCOS ORLAMUNDER";
		String[] firstName;
		
		firstName = extractFirstName.firstNameExtractor(name);
		System.out.println(firstName[0]);
		MatcherAssert.assertThat(firstName[0], Matchers.equalToIgnoringCase("ABNER"));
		
	}
}
