package com.javangarda.fantacalcio.web.validators;

import com.javangarda.fantacalcio.util.messages.ValidationMessages;

import lombok.Getter;

public class GuiDtoNotValidException extends Exception {

	@Getter
	private ValidationMessages validationMessages;
	
	public GuiDtoNotValidException(ValidationMessages validationMessages){
		this.validationMessages = validationMessages;
	}
}
