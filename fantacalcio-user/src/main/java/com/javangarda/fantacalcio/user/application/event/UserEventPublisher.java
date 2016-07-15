package com.javangarda.fantacalcio.user.application.event;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingGateway
public interface UserEventPublisher {

	void publishUserCreated(String email);
	
	void publishEmailTokenCreated(@Payload("email") String email, @Header("activationToken") String activationToken);
}
