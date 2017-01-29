package com.javangarda.fantacalcio.user.application.internal.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.internal.UserFactory;
import com.javangarda.fantacalcio.user.application.model.User;

@Component
public class SimpleUserFactory implements UserFactory {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User create(RegistrationUserDTO registrationUserDto) {
		User user = new User(generateId());
		user.register(registrationUserDto.getFullName(), passwordEncoder.encode(registrationUserDto.getPassword()));
		return user;
	}

	protected String generateId() {
		return UUID.randomUUID().toString();
	}
}
