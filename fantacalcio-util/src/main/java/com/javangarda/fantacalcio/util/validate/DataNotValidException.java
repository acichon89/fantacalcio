package com.javangarda.fantacalcio.util.validate;

import com.javangarda.fantacalcio.util.messages.ValidationMessages;

import lombok.Getter;

public class DataNotValidException extends Exception {

	@Getter
	private ValidationMessages validationMessages;
	
	public DataNotValidException(ValidationMessages validationMessages){
		this.validationMessages = validationMessages;
	}
}
