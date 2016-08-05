package com.javangarda.fantacalcio.user.application.event;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface EmailCommandSender {

	void sendConfirmationEmail(String email, String token);
}
