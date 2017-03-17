package com.javangarda.fantacalcio.user.application.saga;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.event.UserCommandSender;
import com.javangarda.fantacalcio.user.application.internal.AuthCommandSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@MessageEndpoint
@Slf4j
public class UserSaga {

	@Autowired
	private UserCommandSender userCommandSender;
	@Autowired
	private AuthCommandSender authCommandSender;

	@ServiceActivator(inputChannel="userRegisteredChannel")
	public void handleUserRegisterEvent(@Payload RegistrationUserDTO registrationUserDto, @Header("userId") String userId) {
		log.trace("Thread = {} @UserEventHandler::handleUserRegisterEvent, registrationUserDto={}, userId={}", Thread.currentThread().getName(), registrationUserDto, userId);
		userCommandSender.createActivationEmailToken(registrationUserDto.getEmail(), userId);
	}

	@ServiceActivator(inputChannel = "userBannedChannel")
	public void handleUserBannedEvent(@Payload String email) {
		authCommandSender.removeAccessToken(email);
	}
}
