package com.javangarda.fantacalcio.user.application.event;

import com.javangarda.fantacalcio.util.i18n.MessageLocalizable;

import lombok.Getter;

public class EmailNotFoundException extends Exception implements MessageLocalizable {

	@Getter
	private String email;
	
	public EmailNotFoundException(String email){
		super("Email '"+email+"' not found in the system");
		this.email=email;
	}

	@Override
	public String getMessageKey() {
		return "user.exception.EmailNotFoundException.messageKey";
	}

}
