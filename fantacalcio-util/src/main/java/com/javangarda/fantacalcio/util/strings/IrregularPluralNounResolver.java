package com.javangarda.fantacalcio.util.strings;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Joiner;

public class IrregularPluralNounResolver extends AbstractPluralNounResolver{

	private static final Map<String, String> EXCEPTIONS;
	
	static {
		EXCEPTIONS = new HashMap<String, String>(); 
		EXCEPTIONS.put("woman","women");
		EXCEPTIONS.put("man","men");
		EXCEPTIONS.put("child","children");
		EXCEPTIONS.put("tooth","teeth");
		EXCEPTIONS.put("foot","feet");
		EXCEPTIONS.put("person","people");
		EXCEPTIONS.put("leaf","leaves");
		EXCEPTIONS.put("mouse","mice");
		EXCEPTIONS.put("goose","geese");
		EXCEPTIONS.put("half","halves");
		EXCEPTIONS.put("knife","knives");
		EXCEPTIONS.put("wife","wives");
		EXCEPTIONS.put("life","lives");
		EXCEPTIONS.put("elf","elves");
		EXCEPTIONS.put("loaf","loaves");
		EXCEPTIONS.put("potato","potatoes");
		EXCEPTIONS.put("tomato","tomatoes");
		EXCEPTIONS.put("cactus","cacti");
		EXCEPTIONS.put("focus","foci");
		EXCEPTIONS.put("fungus","fungi");
		EXCEPTIONS.put("nucleus","nuclei");
		EXCEPTIONS.put("syllabus","syllabi/syllabuses");
		EXCEPTIONS.put("analysis","analyses");
		EXCEPTIONS.put("diagnosis","diagnoses");
		EXCEPTIONS.put("oasis","oases");
		EXCEPTIONS.put("thesis","theses");
		EXCEPTIONS.put("crisis","crises");
		EXCEPTIONS.put("phenomenon","phenomena");
		EXCEPTIONS.put("criterion","criteria");
		EXCEPTIONS.put("datum","data");
		
	};

	@Override
	protected boolean canModify(String noun) {
		return EXCEPTIONS.containsKey(lastUnderscoredWord(noun));
	}
	
	@Override
	protected String modify(String noun) {
		String[] underscoredNouns = noun.split("_");
		int lastIndex = underscoredNouns.length-1;
		underscoredNouns[lastIndex] = EXCEPTIONS.get(underscoredNouns[lastIndex]);
		return Joiner.on("_").join(underscoredNouns);
	}

	private String lastUnderscoredWord(String noun) {
		String[] underscoredNouns = noun.split("_");
		return underscoredNouns[underscoredNouns.length-1];
	}

}
