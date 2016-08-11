package com.javangarda.fantacalcio.user.application.gateway.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
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

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
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
	public void registerUser(@Payload RegistrationUserDto registrationUserDto) throws DuplicateEmailException {
		log.debug("Thread = {} @EventDrivenUserGateway::registerUser, registrationUserDto={}", Thread.currentThread().getName(), registrationUserDto);
		userService.registerUser(registrationUserDto);
		userEventPublisher.publishUserCreated(registrationUserDto);
	}

	@ServiceActivator(inputChannel="createActivationEmailTokenChannel")
	@Override
	public void startConfirmationEmailProcedure(@Payload String mail) throws EmailNotFoundException {
		log.debug("Thread = {} @EventDrivenUserGateway::startConfirmationEmailProcedure, mail={}", Thread.currentThread().getName(), mail);
		String token = userService.assignActivationToken(mail);
		emailCommandSender.sendConfirmationEmail(mail, token);
	}

	@ServiceActivator(inputChannel="saveConnectionChannel")
	@Override
	public void saveConnection(@Payload Connection connection, @Header("email") String email) {
		log.debug("Thread = {} @EventDrivenUserGateway::saveConnection, mail={}, connection={}", Thread.currentThread().getName(), email, connection);
		userConnectionService.saveUserConnection(email, connection);
	}

}
