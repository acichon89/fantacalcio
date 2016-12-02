package com.javangarda.fantacalcio.mail.application.gateway.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.mail.application.data.EmailMessageDTO;
import com.javangarda.fantacalcio.mail.application.data.MailContent;
import com.javangarda.fantacalcio.mail.application.gateway.EmailGateway;
import com.javangarda.fantacalcio.mail.application.internal.MailContentProvider;
import com.javangarda.fantacalcio.mail.application.internal.MailSender;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailGatewayImpl implements EmailGateway {

	@Autowired
	private MailSender mailSender;
	@Autowired
	private MailContentProvider mailContentProvider;
	@Autowired
	private MessageSource messageSource;
	
	private static final Locale DEFAULT_LOCALE = new Locale("en");
	
	@Override
	public void sendActivationEmail(String email, String activationToken) {
		String title = messageSource.getMessage("activationMail.title", null, DEFAULT_LOCALE);
		MailContent mailContent = mailContentProvider.provideActivationMailContent(DEFAULT_LOCALE, email, activationToken);
		EmailMessageDTO emailDTO = new EmailMessageDTO(title, email, mailContent);
		mailSender.sendEmail(emailDTO);
	}
}
