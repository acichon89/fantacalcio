package com.javangarda.fantacalcio.user.application.event;

import com.javangarda.fantacalcio.util.i18n.MessageLocalizable;

public class DuplicateEmailException extends Exception implements MessageLocalizable {

	private final String duplicatedEmail;
	
	public DuplicateEmailException(String duplicatedEmail){
		super("Trying to register user with existing email");
		this.duplicatedEmail=duplicatedEmail;
	}

	@Override
	public String getMessageKey() {
		return "user.exception.DuplicateEmailException.messageKey";
	}

	@Override
	public Object[] getArgs() {
		return new Object[] {duplicatedEmail};
	}

}
