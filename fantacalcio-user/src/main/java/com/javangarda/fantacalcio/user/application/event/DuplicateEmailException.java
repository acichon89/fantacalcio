package com.javangarda.fantacalcio.user.application.event;

import com.javangarda.fantacalcio.util.i18n.MessageLocalizable;

import lombok.Getter;

public class DuplicateEmailException extends Exception implements MessageLocalizable {

	@Getter
	private String duplicatedEmail;
	
	public DuplicateEmailException(String duplicatedEmail){
		super("Trying to register user with existing email");
		this.duplicatedEmail=duplicatedEmail;
	}

	@Override
	public String getMessageKey() {
		return "user.exception.DuplicateEmailException.messageKey";
	}

}
