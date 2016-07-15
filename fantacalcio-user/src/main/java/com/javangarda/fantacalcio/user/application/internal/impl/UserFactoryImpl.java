package com.javangarda.fantacalcio.user.application.internal.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.EstablishedUserSocialConnection;
import com.javangarda.fantacalcio.user.application.internal.UserFactory;
import com.javangarda.fantacalcio.user.application.model.entity.User;
import com.javangarda.fantacalcio.user.application.model.value.Role;

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
