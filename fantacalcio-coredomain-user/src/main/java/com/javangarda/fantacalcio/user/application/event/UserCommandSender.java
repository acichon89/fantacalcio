package com.javangarda.fantacalcio.user.application.event;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingGateway
public interface UserCommandSender {

	@Gateway(requestChannel="createActivationEmailTokenChannel")
	void createActivationEmailToken(@Payload String email, @Header("userId") String userId);
}
