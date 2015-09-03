package com.javangarda.fantacalcio.util.strings;

public class RegularPluralNounResolver extends AbstractPluralNounResolver{

	private static final String PLURAL_SUFFIX = "s";
	
	@Override
	protected String modify(String noun) {
		return noun += PLURAL_SUFFIX;
	}

	@Override
	protected boolean canModify(String noun) {
		return true;
	}

}
