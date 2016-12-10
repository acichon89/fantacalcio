package com.javangarda.fantacalcio.user.application.internal.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.extras.Converter;
import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.internal.UserFactory;
import com.javangarda.fantacalcio.user.application.model.Role;
import com.javangarda.fantacalcio.user.application.model.User;
import com.javangarda.fantacalcio.user.application.model.UserStatus;

@Component
public class SimpleUserFactory implements UserFactory {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private Converter<RegistrationUserDTO, User> userRegistrationConverter;

	@Override
	public User create(RegistrationUserDTO registrationUserDto) {
		User user = new User();
		user.setId(generateId());
		user.addRole(Role.ROLE_USER);
		user.setStatus(UserStatus.NOT_CONFIRMED);
		user.merge(userRegistrationConverter.convertTo(registrationUserDto), true);
		user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
		return user;
	}

	/*@Override
	public User create(SignUpSocialConnection connection) {
		User newUser = new User();
		newUser.setId(generateId());
		newUser.setEmail(connection.getEmail());
		newUser.setFullName(connection.getFullName());
		newUser.addRole(Role.ROLE_USER);
		newUser.setStatus(UserStatus.CONFIRMED);
		return newUser;
	}*/
	
	private String generateId() {
		return UUID.randomUUID().toString();
	}
}