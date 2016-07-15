package com.javangarda.fantacalcio.user.application.gateway.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDto;
import com.javangarda.fantacalcio.user.application.event.DuplicateEmailException;
import com.javangarda.fantacalcio.user.application.event.EmailNotFoundException;
import com.javangarda.fantacalcio.user.application.event.UserEventPublisher;
import com.javangarda.fantacalcio.user.application.gateway.UserGateway;
import com.javangarda.fantacalcio.user.application.internal.UserService;

@Component
public class EventDrivenUserGateway implements UserGateway {

	@Autowired
	private UserService userService;
	@Autowired
	private UserEventPublisher userEventPublisher;
	
	@Override
	public void registerUser(RegistrationUserDto registrationUserDto) throws DuplicateEmailException {
		userService.registerUser(registrationUserDto);
		userEventPublisher.publishUserCreated(registrationUserDto.getEmail());
	}

	@Override
	public void confirmEmail(String mail) throws EmailNotFoundException {
		String token = userService.assignActivationToken(mail);
		userEventPublisher.publishEmailTokenCreated(mail, token);
	}

}
