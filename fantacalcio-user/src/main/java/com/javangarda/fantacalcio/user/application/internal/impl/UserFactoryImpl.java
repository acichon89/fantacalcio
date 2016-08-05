package com.javangarda.fantacalcio.user.application.internal.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.SignUpSocialConnection;
import com.javangarda.fantacalcio.user.application.internal.UserFactory;
import com.javangarda.fantacalcio.user.application.model.entity.User;
import com.javangarda.fantacalcio.user.application.model.value.Role;
import com.javangarda.fantacalcio.user.application.model.value.UserStatus;

@Component
public class UserFactoryImpl implements UserFactory {

	@Override
	public User create() {
		User user = new User();
		user.setId(generateId());
		user.addRole(Role.ROLE_USER);
		user.setStatus(UserStatus.NOT_CONFIRMED);
		return user;
	}

	@Override
	public User create(SignUpSocialConnection connection) {
		User newUser = new User();
		newUser.setId(generateId());
		newUser.setEmail(connection.getEmail());
		newUser.setFullName(connection.getFullName());
		newUser.addRole(Role.ROLE_USER);
		newUser.setStatus(UserStatus.CONFIRMED);
		return newUser;
	}
	
	private String generateId() {
		return UUID.randomUUID().toString();
	}
}
