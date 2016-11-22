package com.javangarda.fantacalcio.user.application.event;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingGateway
public interface EmailCommandSender {

	@Gateway(requestChannel="sendingConfirmationEmailChannel")
	void sendConfirmationEmail(@Payload String email, @Header("activationToken") String token);
}
