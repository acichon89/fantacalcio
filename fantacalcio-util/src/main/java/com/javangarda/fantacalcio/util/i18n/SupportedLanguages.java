package com.javangarda.fantacalcio.util.i18n;

import java.util.Locale;

import lombok.Getter;

public enum SupportedLanguages {

	ENGLISH("en_GB"), POLISH("pl_PL");
	
	private SupportedLanguages(String lng) {
		this.locale = new Locale(lng);
	}
	
	@Getter
	public Locale locale;
}
