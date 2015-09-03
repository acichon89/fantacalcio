package com.javangarda.fantacalcio.util.strings;

public class EsPluralNounResolver extends AbstractPluralNounResolver{

	private static final String[] NOUN_ENDINGS_FOR_ES = {"s", "x", "z", "ch", "sh"};
	
	private static final String SUFIX = "es";
	
	@Override
	protected boolean canModify(String noun) {
		for(String ending : NOUN_ENDINGS_FOR_ES){
			if(noun.endsWith(ending)){
				return true;
			}
		}
		return false;
	}

	@Override
	protected String modify(String noun) {
		return noun += SUFIX;
	}

}
