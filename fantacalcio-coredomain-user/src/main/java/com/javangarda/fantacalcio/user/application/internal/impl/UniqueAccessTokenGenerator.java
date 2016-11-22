package com.javangarda.fantacalcio.user.application.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.internal.AccessTokenGenerator;
import com.javangarda.fantacalcio.user.application.internal.RandomStringGenerator;
import com.javangarda.fantacalcio.user.application.internal.UserRepository;

@Component
public class UniqueAccessTokenGenerator implements AccessTokenGenerator {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RandomStringGenerator randomStringGenerator;

	@Override
	public String createConfirmEmailToken() {
		String token = null;
		do {
			token = randomStringGenerator.generateRandomAlphaNumericString(25);
		} while (userRepository.countUserWithConfirmEmailToken(token) > 0);
		return token;
	}

	@Override
	public String createResetPasswordToken() {
		return null;
	}
	
	
}
