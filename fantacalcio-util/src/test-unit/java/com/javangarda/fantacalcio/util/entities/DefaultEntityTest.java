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
	
	@Test
	public void mergeTest() {
		Donkey donkey = new Donkey();
		donkey.setId(123l);
		donkey.setVersion(2l);
		
		Donkey other = new Donkey();
		other.setVersion(5l);
		
		donkey.merge(other);
		Assert.assertNull(donkey.getId());
		Assert.assertEquals(Long.valueOf(5l), donkey.getVersion());
	}

}
class Donkey extends DefaultEntity<Long> {
	
}

class Doge extends DefaultEntity<Long> {
	
}