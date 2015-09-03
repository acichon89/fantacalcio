package com.javangarda.fantacalcio.util.strings;

public class IesPluralNounResolver extends AbstractPluralNounResolver{

	private static final String NOUN_ENDINGS_FOR_IES = "y";
	
	private static final String SUFIX = "ies";
	
	@Override
	protected boolean canModify(String noun) {
		return noun.endsWith(NOUN_ENDINGS_FOR_IES);
	}

	@Override
	protected String modify(String noun) {
		StringBuilder b = new StringBuilder(noun);
		b.replace(noun.lastIndexOf(NOUN_ENDINGS_FOR_IES), noun.lastIndexOf(NOUN_ENDINGS_FOR_IES) + 1, SUFIX );
		return b.toString();
	}
	

}
