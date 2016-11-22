package com.javangarda.fantacalcio.user.application.gateway.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.event.DuplicateEmailException;
import com.javangarda.fantacalcio.user.application.event.EmailCommandSender;
import com.javangarda.fantacalcio.user.application.event.EmailNotFoundException;
import com.javangarda.fantacalcio.user.application.event.UserEventPublisher;
import com.javangarda.fantacalcio.user.application.gateway.UserGateway;
import com.javangarda.fantacalcio.user.application.internal.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EventDrivenUserGateway implements UserGateway {

	@Autowired
	private UserEventPublisher userEventPublisher;
	@Autowired
	private EmailCommandSender emailCommandSender;
	@Autowired
	private UserService userService;
	
	@Override
	public void registerUser(RegistrationUserDTO registrationUserDTO) throws DuplicateEmailException {
		log.debug("Thread = {} @EventDrivenUserGateway::registerUser, registrationUserDto={}", Thread.currentThread().getName(), registrationUserDTO);
		userService.registerUser(registrationUserDTO);
		userEventPublisher.publishUserCreated(registrationUserDTO);
	}

	@Override
	@ServiceActivator(inputChannel="createActivationEmailTokenChannel")
	public void startConfirmationEmailProcedure(String mail) throws EmailNotFoundException {
		log.debug("Thread = {} @EventDrivenUserGateway::startConfirmationEmailProcedure, mail={}", Thread.currentThread().getName(), mail);
		String token = userService.assignActivationToken(mail);
		emailCommandSender.sendConfirmationEmail(mail, token);
	}

}
