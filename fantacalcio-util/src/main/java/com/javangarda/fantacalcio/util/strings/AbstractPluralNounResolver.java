package com.javangarda.fantacalcio.util.strings;

import lombok.Setter;

/**
 * based on http://www.edufind.com/english-grammar/plural-nouns/
 * @author AndrzejCichon
 *
 */
public abstract class AbstractPluralNounResolver {

	@Setter
	private AbstractPluralNounResolver nextResolver;
	
	public String resolve(String noun){
		if(canModify(noun)){
			return modify(noun);
		} else if(nextResolver!=null){
			return nextResolver.resolve(noun);
		} return noun;
	}
	
	abstract protected boolean canModify(String noun);
	
	abstract protected String modify(String noun);
}
