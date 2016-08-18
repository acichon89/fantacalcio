package com.javangarda.fantacalcio.util.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

import lombok.Data;

public class ObjectUtilsTest {

	@Test
	public void test() {
		Bear grizzly = new Bear();
		grizzly.setClaws(24);
		grizzly.setName("Grizzly");
		
		String[] nullPropertyNames = ObjectUtils.getNullPropertyNames(grizzly);
		assertTrue(ArrayUtils.isEmpty(nullPropertyNames));
		
		Bear teddy = new Bear();
		teddy.setName("Teddy bear");
		nullPropertyNames = ObjectUtils.getNullPropertyNames(teddy);
		assertEquals(1, nullPropertyNames.length);
		assertEquals("claws", nullPropertyNames[0]);
		
	}

}
@Data
class Bear {
	private String name;
	private Integer claws;
}