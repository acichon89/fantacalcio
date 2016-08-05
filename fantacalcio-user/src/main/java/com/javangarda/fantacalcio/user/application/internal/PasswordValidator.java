package com.javangarda.fantacalcio.user.application.internal;

import com.javangarda.fantacalcio.util.validate.DataNotValidException;

public interface PasswordValidator {

	int MIN_LENGTH						= 8;
	int MIN_DIGITS_COUNT				= 1;
	boolean MUST_CONTAINS_SPECIAL_CHARACTER	= true;
	char[] SPECIAL_CHARACTERS = {'!', '@', '#', '$', '%', '^', '&', '*', '+', '-'};
	
	
	void validate(String password) throws DataNotValidException;
}
