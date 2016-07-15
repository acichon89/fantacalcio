package com.javangarda.fantacalcio.user.application.data;

import lombok.Data;

@Data
public class RegistrationUserDto {

	private String email;
	private String fullName;
	private String password;
	private String confirmedPassword;
}
