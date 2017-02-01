package com.javangarda.fantacalcio.user.application.internal.impl;

import com.javangarda.fantacalcio.user.application.data.FantaCalcioUser;
import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.internal.AccessTokenGenerator;
import com.javangarda.fantacalcio.user.application.internal.UserFactory;
import com.javangarda.fantacalcio.user.application.internal.UserRepository;
import com.javangarda.fantacalcio.user.application.internal.UserService;
import com.javangarda.fantacalcio.user.application.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
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
	public String registerUser(RegistrationUserDTO registrationUserDto) {
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
	public Optional<FantaCalcioUser> confirmEmail(String activationToken) {
		Optional<User> u = userRepository.findByConfirmEmailToken(activationToken);
		u.ifPresent(user -> user.confirmEmail());
		return u.map(FantaCalcioUser::new);
	}

	@Override
	public void changePassword(String newPassword, String userEmail) {
		String encodedPassword = passwordEncoder.encode(newPassword);
		userRepository.findByEmail(userEmail).ifPresent(user -> user.changePassword(encodedPassword, false));
	}

	@Override
	public void resetPassword(String newPassword, String userEmail) {
		String encodedPassword = passwordEncoder.encode(newPassword);
		userRepository.findByEmail(userEmail).ifPresent(user -> user.changePassword(encodedPassword, true));
	}

	@Override
	public void banUser(String email) {
		userRepository.findByEmail(email).ifPresent(user -> user.ban());
	}

	@Override
	public Optional<FantaCalcioUser> getById(String id) {
		return userRepository.findOne(id).map(FantaCalcioUser::new);
	}
}
