package com.javangarda.fantacalcio.user.application.event;

public interface EmailCommandSender {

	void sendConfirmationEmail(String email, String token);
}
