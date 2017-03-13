package com.javangarda.fantacalcio.authserver.application.model;

import lombok.Getter;

public enum UserStatus {
	NOT_CONFIRMED(false), CONFIRMED(true), BANNED(false);

	private UserStatus(boolean allowed){
		this.allowed=allowed;
	}

	@Getter
	private boolean allowed;
}
