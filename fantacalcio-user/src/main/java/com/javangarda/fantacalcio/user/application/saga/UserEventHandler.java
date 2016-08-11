package com.javangarda.fantacalcio.user.application.saga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.social.connect.Connection;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDto;
import com.javangarda.fantacalcio.user.application.event.UserCommandSender;

import lombok.extern.slf4j.Slf4j;

@MessageEndpoint
@Slf4j
public class UserEventHandler {

	@Autowired
	private UserCommandSender userCommandSender;
	
	@ServiceActivator(inputChannel="userRegisteredChannel")
	public void handleUserRegisterEvent(@Payload RegistrationUserDto registrationUserDto) {
		log.trace("Thread = {} @UserEventHandler::handleUserRegisterEvent, registrationUserDto={}", Thread.currentThread().getName(), registrationUserDto);
		userCommandSender.createActivationEmailToken(registrationUserDto.getEmail());
		if(registrationUserDto.getConnection().isPresent()){
			Connection connection = registrationUserDto.getConnection().get();
			userCommandSender.saveConnection(connection, registrationUserDto.getEmail());
		}
	}
}
