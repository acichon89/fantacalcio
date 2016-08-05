package com.javangarda.fantacalcio.user.application.saga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.social.connect.Connection;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDto;
import com.javangarda.fantacalcio.user.application.event.UserCommandSender;

@MessageEndpoint
public class UserEventHandler {

	@Autowired
	private UserCommandSender userCommandSender;
	
	public void handleUserRegisterEvent(RegistrationUserDto registrationUserDto) {
		userCommandSender.createActivationEmailToken(registrationUserDto.getEmail());
		if(registrationUserDto.getConnection().isPresent()){
			Connection connection = registrationUserDto.getConnection().get();
			userCommandSender.saveConnection(registrationUserDto.getEmail(), connection);
		}
	}
}
