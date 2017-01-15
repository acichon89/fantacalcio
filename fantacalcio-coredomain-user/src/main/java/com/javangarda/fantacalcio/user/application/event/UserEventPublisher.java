package com.javangarda.fantacalcio.user.application.event;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;

@MessagingGateway
public interface UserEventPublisher {

	@Gateway(requestChannel="userRegisteredChannel")
	void publishUserCreated(@Payload RegistrationUserDTO registerDto, @Header("userId") String id);

	@Gateway(requestChannel = "emailConfirmedChannel")
	void publishEmailConfirmed(String email);
}
