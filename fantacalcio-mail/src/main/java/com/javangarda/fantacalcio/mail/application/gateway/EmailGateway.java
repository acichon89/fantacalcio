package com.javangarda.fantacalcio.mail.application.gateway;

import java.util.Locale;

import com.javangarda.fantacalcio.util.i18n.SupportedLanguages;

public interface EmailGateway {
	
	Locale DEFAULT_MAIL_LOCALE = SupportedLanguages.ENGLISH.getLocale();

	void sendActivationEmail(String email, String activationHash);
}
