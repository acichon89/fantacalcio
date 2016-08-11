package com.javangarda.fantacalcio.user.application.event;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDto;

@MessagingGateway
public interface UserEventPublisher {

	@Gateway(requestChannel="userRegisteredChannel")
	void publishUserCreated(@Payload RegistrationUserDto registerDto);
	@Gateway(requestChannel="activat")
	void publishActivationTokenAssigned(@Payload String activationToken, @Header("email") String email);
}
