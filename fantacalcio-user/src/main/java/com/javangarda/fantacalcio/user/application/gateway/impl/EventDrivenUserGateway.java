package com.javangarda.fantacalcio.user.application.gateway.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDto;
import com.javangarda.fantacalcio.user.application.event.DuplicateEmailException;
import com.javangarda.fantacalcio.user.application.event.EmailCommandSender;
import com.javangarda.fantacalcio.user.application.event.EmailNotFoundException;
import com.javangarda.fantacalcio.user.application.event.UserEventPublisher;
import com.javangarda.fantacalcio.user.application.gateway.UserGateway;
import com.javangarda.fantacalcio.user.application.internal.UserConnectionService;
import com.javangarda.fantacalcio.user.application.internal.UserService;

@Component
public class EventDrivenUserGateway implements UserGateway {

	@Autowired
	private UserService userService;
	@Autowired
	private UserConnectionService userConnectionService;
	@Autowired
	private UserEventPublisher userEventPublisher;
	@Autowired
	private EmailCommandSender emailCommandSender;
	
	@Override
	public void registerUser(RegistrationUserDto registrationUserDto) throws DuplicateEmailException {
		userService.registerUser(registrationUserDto);
		userEventPublisher.publishUserCreated(registrationUserDto);
	}

	@Override
	public void startConfirmationEmailProcedure(String mail) throws EmailNotFoundException {
		String token = userService.assignActivationToken(mail);
		emailCommandSender.sendConfirmationEmail(mail, token);
	}

	@Override
	public void saveConnection(String email, Connection connection) {
		userConnectionService.saveUserConnection(email, connection);
	}

}
