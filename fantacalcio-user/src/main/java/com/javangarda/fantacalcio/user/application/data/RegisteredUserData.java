package com.javangarda.fantacalcio.user.application.data;

import lombok.Data;

@Data
public class RegisteredUserData {

	private String email;
	private String activationToken;
}
