package com.javangarda.fantacalcio.user.application.data;

import java.util.Optional;

import org.springframework.social.connect.Connection;

import lombok.Data;

@Data
public class RegistrationUserDto {

	private String email;
	private String fullName;
	private String password;
	private String confirmedPassword;
	private Optional<Connection<?>> connection;
}
