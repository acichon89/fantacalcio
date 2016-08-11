package com.javangarda.fantacalcio.user.application.event;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.social.connect.Connection;

@MessagingGateway
public interface UserCommandSender {

	@Gateway(requestChannel="saveConnectionChannel")
	void saveConnection(@Payload Connection connection, @Header("email") String email);
	@Gateway(requestChannel="createActivationEmailTokenChannel")
	void createActivationEmailToken(@Payload String email);
}
