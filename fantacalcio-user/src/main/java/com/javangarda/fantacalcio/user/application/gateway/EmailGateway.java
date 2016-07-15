package com.javangarda.fantacalcio.user.application.gateway;

public interface EmailGateway {

	void sendActivationEmail(String email, String activationHash);
}
