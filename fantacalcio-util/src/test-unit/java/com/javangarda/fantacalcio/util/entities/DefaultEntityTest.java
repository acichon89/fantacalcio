package com.javangarda.fantacalcio.util.entities;

import org.junit.Assert;
import org.junit.Test;

public class DefaultEntityTest {

	@Test
	public void equalsTest() {
		Donkey donkey = new Donkey();
		donkey.setId(1l);
		Doge doge = new Doge();
		doge.setId(1l);
		Assert.assertFalse(donkey.equals(doge));
		
		Doge helloThisIsDoge = new Doge();
		helloThisIsDoge.setId(null);
		Assert.assertFalse(doge.equals(helloThisIsDoge));
		
		helloThisIsDoge.setId(1l);
		Assert.assertTrue(doge.equals(helloThisIsDoge));
	}

}
class Donkey extends DefaultEntity<Long> {
	
}

class Doge extends DefaultEntity<Long> {
	
}