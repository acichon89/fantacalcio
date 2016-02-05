package com.javangarda.fantacalcio.util.entities;

import org.hibernate.cfg.ImprovedNamingStrategy;

public class FantaCalcioEntitiesNamingStrategy extends ImprovedNamingStrategy {

	@Override
	public String classToTableName(String className) {
		EntityTableNameResolver nameResolver = new EntityTableNameResolver();
		return nameResolver.toTableName(className);
	}
	
}
