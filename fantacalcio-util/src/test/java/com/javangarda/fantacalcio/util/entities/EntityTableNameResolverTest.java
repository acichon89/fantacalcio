package com.javangarda.fantacalcio.util.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class EntityTableNameResolverTest {

	@Test
	public void testToTableName() {
		EntityTableNameResolver entityTableNameResolver = new EntityTableNameResolver();
		
		assertEquals("users", entityTableNameResolver.toTableName("User"));
		assertEquals("animals", entityTableNameResolver.toTableName("Animal"));
		assertEquals("classes", entityTableNameResolver.toTableName("Class"));
		assertEquals("order_parts", entityTableNameResolver.toTableName("OrderPart"));
		assertEquals("camel_case_classes", entityTableNameResolver.toTableName("CamelCaseClass"));
		assertEquals("suffixes", entityTableNameResolver.toTableName("Suffix"));
		assertEquals("pennies", entityTableNameResolver.toTableName("Penny"));
		assertEquals("power_people", entityTableNameResolver.toTableName("PowerPerson"));
		assertEquals("beauty_daisies", entityTableNameResolver.toTableName("BeautyDaisy"));
		assertEquals("high_elves", entityTableNameResolver.toTableName("HighElf"));
		assertEquals("young_and_from_big_town_people", entityTableNameResolver.toTableName("YoungAndFromBigTownPerson"));
	}

}
