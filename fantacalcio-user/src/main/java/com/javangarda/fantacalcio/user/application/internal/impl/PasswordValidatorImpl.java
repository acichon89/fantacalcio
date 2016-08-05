package com.javangarda.fantacalcio.user.application.internal.impl;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.internal.PasswordValidator;
import com.javangarda.fantacalcio.util.messages.Message;
import com.javangarda.fantacalcio.util.messages.Severity;
import com.javangarda.fantacalcio.util.messages.ValidationMessages;
import com.javangarda.fantacalcio.util.validate.DataNotValidException;

@Component
public class PasswordValidatorImpl implements PasswordValidator {

	public static final String PASSWORD_PARAM = "password";
	
	@Override
	public void validate(String password) throws DataNotValidException {
		
		ValidationMessages messages = ValidationMessages.createEmpty();
		if(StringUtils.isBlank(password)){
			messages.add(PASSWORD_PARAM, Message.of(Severity.ERROR, "validator.password.null"));
		}
		else {
			if(password.length()<MIN_LENGTH){
				messages.add(PASSWORD_PARAM, Message.of(Severity.ERROR, "validator.password.minlength", MIN_LENGTH));
			}
			if(MUST_CONTAINS_SPECIAL_CHARACTER && !passwordContainsSpecialCharacter(password)) {
				messages.add(PASSWORD_PARAM, Message.of(Severity.ERROR, "validator.password.notcontainsspecial", Arrays.toString(SPECIAL_CHARACTERS)));
			}
			if(getCountOfDigits(password) < MIN_DIGITS_COUNT) {
				messages.add(PASSWORD_PARAM, Message.of(Severity.ERROR, "validator.password.notEnoughDigits", MIN_DIGITS_COUNT));
			}
		}
		if(!messages.isEmpty()){
			throw new DataNotValidException(messages);
		}
	}

	private boolean passwordContainsSpecialCharacter(String password) {
		for(char c : SPECIAL_CHARACTERS){
			if(password.indexOf(c)>=0){
				return true;
			}
		}
		return false;
	}
	
	private int getCountOfDigits(String password) {
		int count = 0;
		for (int i = 0, len = password.length(); i < len; i++) {
		    if (Character.isDigit(password.charAt(i))) {
		        count++;
		    }
		}
		return count;
	}

}
