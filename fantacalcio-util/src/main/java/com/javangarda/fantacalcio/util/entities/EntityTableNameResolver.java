package com.javangarda.fantacalcio.util.entities;

import com.google.common.base.CaseFormat;
import com.javangarda.fantacalcio.util.strings.AbstractPluralNounResolver;
import com.javangarda.fantacalcio.util.strings.EsPluralNounResolver;
import com.javangarda.fantacalcio.util.strings.IesPluralNounResolver;
import com.javangarda.fantacalcio.util.strings.IrregularPluralNounResolver;
import com.javangarda.fantacalcio.util.strings.RegularPluralNounResolver;

public class EntityTableNameResolver {
	

	public String toTableName(String entityName){
		String formattedName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entityName);
		return createChainOfResolvers().resolve(formattedName);
	}
	
	private AbstractPluralNounResolver createChainOfResolvers() {
		AbstractPluralNounResolver regularResolver = new RegularPluralNounResolver();
		AbstractPluralNounResolver esPluralNounResolver = new EsPluralNounResolver();
		AbstractPluralNounResolver iesPluralNounResolver = new IesPluralNounResolver();
		AbstractPluralNounResolver irregularPluralNounResolver = new IrregularPluralNounResolver();
		
		irregularPluralNounResolver.setNextResolver(iesPluralNounResolver);
		iesPluralNounResolver.setNextResolver(esPluralNounResolver);
		esPluralNounResolver.setNextResolver(regularResolver);
		
		return irregularPluralNounResolver;
	}
}
