package com.javangarda.fantacalcio.util.validate;

import com.javangarda.fantacalcio.util.messages.ValidationMessages;

import lombok.Getter;

public class DataNotValidException extends Exception {

	private static final long serialVersionUID = -6700429001442465497L;
	
	@Getter
	private final ValidationMessages validationMessages;
	
	public DataNotValidException(ValidationMessages validationMessages){
		this.validationMessages = validationMessages;
	}
}
