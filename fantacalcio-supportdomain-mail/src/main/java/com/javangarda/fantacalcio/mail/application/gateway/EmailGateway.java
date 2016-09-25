package com.javangarda.fantacalcio.mail.application.gateway;

public interface EmailGateway {
	
	void sendActivationEmail(String email, String activationHash);
}
