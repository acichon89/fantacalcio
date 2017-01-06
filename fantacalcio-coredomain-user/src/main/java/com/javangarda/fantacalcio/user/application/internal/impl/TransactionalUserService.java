package com.javangarda.fantacalcio.user.application.internal.impl;

import javax.transaction.Transactional;

import com.javangarda.fantacalcio.user.application.data.FantaCalcioUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.event.DuplicateEmailException;
import com.javangarda.fantacalcio.user.application.event.EmailNotFoundException;
import com.javangarda.fantacalcio.user.application.internal.AccessTokenGenerator;
import com.javangarda.fantacalcio.user.application.internal.UserFactory;
import com.javangarda.fantacalcio.user.application.internal.UserRepository;
import com.javangarda.fantacalcio.user.application.internal.UserService;
import com.javangarda.fantacalcio.user.application.model.User;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Component
@Transactional
@Slf4j
public class TransactionalUserService implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserFactory userFactory;
	@Autowired
	private AccessTokenGenerator accessTokenGenerator;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String registerUser(RegistrationUserDTO registrationUserDto) throws DuplicateEmailException {
		int noOfUsersWithTheSameEmail = userRepository.countUserWithEmail(registrationUserDto.getEmail());
		if(noOfUsersWithTheSameEmail > 0) {
			throw new DuplicateEmailException(registrationUserDto.getEmail());
		}
		User user = userFactory.create(registrationUserDto);
		userRepository.save(user);
		return user.getId();
	}
	
	@Override
	public String assignActivationToken(String email, String userId) {
		Optional<User> user = userRepository.findOne(userId);
		String token = accessTokenGenerator.createConfirmEmailToken();
		user.ifPresent(u -> u.assignEmailToBeConfirmed(email, token));
		return token;
	}

	@Override
	public String confirmEmail(String activationToken) throws EmailNotFoundException {
		Optional<User> user = userRepository.findByConfirmEmailToken(activationToken);
		if(user.isPresent()){
			User u = user.get();
			u.confirmEmail();
			return u.getEmail();
		}
		throw new EmailNotFoundException(null);
	}

	@Override
	public void changePassword(String newPassword, String userId) {
		String encodedPassword = passwordEncoder.encode(newPassword);
		userRepository.putPassword(encodedPassword, userId);
	}

	@Override
	public Optional<FantaCalcioUser> getById(String id) {
		Optional<User> user = userRepository.findOne(id);
		FantaCalcioUser fcUser = user.isPresent() ? null : new FantaCalcioUser(user.get());
		return Optional.ofNullable(fcUser);
	}
}
