package com.javangarda.fantacalcio.user.application.internal.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDto;
import com.javangarda.fantacalcio.user.application.event.DuplicateEmailException;
import com.javangarda.fantacalcio.user.application.event.EmailNotFoundException;
import com.javangarda.fantacalcio.user.application.internal.AccessTokenGenerator;
import com.javangarda.fantacalcio.user.application.internal.UserFactory;
import com.javangarda.fantacalcio.user.application.internal.UserRepository;
import com.javangarda.fantacalcio.user.application.internal.UserService;
import com.javangarda.fantacalcio.user.application.model.entity.User;
import com.javangarda.fantacalcio.user.application.model.value.Role;
import com.javangarda.fantacalcio.util.convert.Converter;

import lombok.extern.slf4j.Slf4j;

@Component
@Transactional
@Slf4j
public class TransactionalUserService implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private Converter<RegistrationUserDto, User> userRegistrationConverter;
	@Autowired
	private UserFactory userFactory;
	@Autowired
	private AccessTokenGenerator accessTokenGenerator;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void registerUser(RegistrationUserDto registrationUserDto) throws DuplicateEmailException {
		int noOfUsersWithTheSameEmail = userRepository.countUserWithEmail(registrationUserDto.getEmail());
		if(noOfUsersWithTheSameEmail > 0) {
			throw new DuplicateEmailException(registrationUserDto.getEmail());
		}
		User user = userFactory.create();
		user.merge(userRegistrationConverter.convertTo(registrationUserDto), true);
		user.addRole(Role.ROLE_USER);
		user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
		userRepository.save(user);
	}
	
	@Override
	public String assignActivationToken(String email) throws EmailNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user!=null){
			String token = accessTokenGenerator.createConfirmEmailToken();
			user.setConfirmEmailToken(token);
			return token;
		}
		throw new EmailNotFoundException(email);
	}
}
