package com.javangarda.fantacalcio.util.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class EntityTableNameResolverTest {

	@Test
	public void testToTableName() {
		assertEquals("users", EntityTableNameResolver.toTableName("User"));
		assertEquals("animals", EntityTableNameResolver.toTableName("Animal"));
		assertEquals("classes", EntityTableNameResolver.toTableName("Class"));
		assertEquals("order_parts", EntityTableNameResolver.toTableName("OrderPart"));
		assertEquals("camel_case_classes", EntityTableNameResolver.toTableName("CamelCaseClass"));
		assertEquals("suffixes", EntityTableNameResolver.toTableName("Suffix"));
		assertEquals("pennies", EntityTableNameResolver.toTableName("Penny"));
		assertEquals("beauty_daisies", EntityTableNameResolver.toTableName("BeautyDaisy"));
		assertEquals("high_elves", EntityTableNameResolver.toTableName("HighElf"));
		assertEquals("young_and_from_big_town_people", EntityTableNameResolver.toTableName("YoungAndFromBigTownPerson"));
	}

}
