package com.javangarda.fantacalcio.user.application.event;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.social.connect.Connection;

@MessagingGateway
public interface UserCommandSender {

	void saveConnection(String email, Connection connection);
	
	void createActivationEmailToken(String email);
}
