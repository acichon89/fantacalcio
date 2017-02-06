package com.javangarda.fantacalcio.user.application.internal.impl;

import com.javangarda.fantacalcio.user.application.internal.AccessTokenGenerator;
import com.javangarda.fantacalcio.user.application.internal.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueAccessTokenGenerator implements AccessTokenGenerator {

	@Autowired
	private UserRepository userRepository;

	@Override
	public String createConfirmEmailToken() {
		String token;
		do {
			token = RandomStringUtils.randomAlphanumeric(25);
		} while (userRepository.countUserWithConfirmEmailToken(token) > 0);
		return token;
	}

}
