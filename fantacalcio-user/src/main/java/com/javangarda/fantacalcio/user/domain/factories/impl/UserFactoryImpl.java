package com.javangarda.fantacalcio.user.domain.factories.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.domain.factories.UserFactory;
import com.javangarda.fantacalcio.user.domain.model.User;
import com.javangarda.fantacalcio.user.domain.value.EstablishedUserSocialConnection;
import com.javangarda.fantacalcio.user.domain.value.Role;

@Component
public class UserFactoryImpl implements UserFactory {

	@Override
	public User create() {
		User user = new User();
		user.setId(generateId());
		return user;
	}

	@Override
	public User create(EstablishedUserSocialConnection connection) {
		User newUser = new User();
		newUser.setId(generateId());
		newUser.setEmail(connection.getEmail());
		newUser.getSocialMediaTypes().add(connection.getSocialMediaType());
		newUser.setFullName(connection.getFullName());
		newUser.getRoles().add(Role.ROLE_USER);
		return newUser;
	}
	
	private String generateId() {
		return UUID.randomUUID().toString();
	}
}
