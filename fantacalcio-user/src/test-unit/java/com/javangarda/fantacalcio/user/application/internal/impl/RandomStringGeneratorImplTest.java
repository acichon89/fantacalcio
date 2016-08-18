package com.javangarda.fantacalcio.user.application.internal.impl;

import org.junit.Assert;
import org.junit.Test;

public class RandomStringGeneratorImplTest {

	private RandomStringGeneratorImpl randomStringGeneratorImpl = new RandomStringGeneratorImpl();
	
	@Test
	public void shouldReturnStringWithProperLength() {
		//given:
		int length = 17;
		//when:
		String returnedValue = randomStringGeneratorImpl.generateRandomAlphaNumericString(length);
		//then:
		Assert.assertNotNull(returnedValue);
		Assert.assertEquals(length, returnedValue.length());
	}

}
