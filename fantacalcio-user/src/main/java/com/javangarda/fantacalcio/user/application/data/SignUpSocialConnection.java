package com.javangarda.fantacalcio.user.application.data;

import lombok.Getter;

public class SignUpSocialConnection {

	@Getter
	private String email;
	@Getter
	private String fullName;
	
	public SignUpSocialConnection(String email, String fullName){
		this.email = email;
		this.fullName = fullName;
	}
}
