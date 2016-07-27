package com.javangarda.fantacalcio.user.application.event;

import com.javangarda.fantacalcio.util.i18n.MessageLocalizable;

public class AccessTokenNotFoundException extends Exception implements MessageLocalizable {

	public AccessTokenNotFoundException(){
		super();
	}

	@Override
	public String getMessageKey() {
		return "user.exception.DuplicateEmailException.messageKey";
	}

	@Override
	public Object[] getArgs() {
		return null;
	}
}
