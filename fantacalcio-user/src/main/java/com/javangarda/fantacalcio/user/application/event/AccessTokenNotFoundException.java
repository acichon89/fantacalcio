package com.javangarda.fantacalcio.user.application.event;

import com.javangarda.fantacalcio.util.i18n.MessageLocalizable;

public class AccessTokenNotFoundException extends Exception implements MessageLocalizable {

	public AccessTokenNotFoundException(String token){
		super("Access token '"+token+"' not found");
	}

	@Override
	public String getMessageKey() {
		return "user.exception.AccessTokenNotFoundException.messageKey";
	}

	@Override
	public Object[] getArgs() {
		return null;
	}
}
