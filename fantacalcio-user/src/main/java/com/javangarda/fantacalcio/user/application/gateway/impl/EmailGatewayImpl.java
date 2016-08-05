package com.javangarda.fantacalcio.user.application.gateway.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.EmailMessageDTO;
import com.javangarda.fantacalcio.user.application.gateway.EmailGateway;
import com.javangarda.fantacalcio.user.application.internal.MailContentProvider;
import com.javangarda.fantacalcio.user.application.internal.MailSender;
import com.javangarda.fantacalcio.user.application.model.value.MailContent;

import lombok.Setter;

@Component
public class EmailGatewayImpl implements EmailGateway {

	@Autowired
	private MailSender mailSender;
	@Autowired
	private MailContentProvider mailContentProvider;
	@Setter
	private MessageSource messageSource;
	
	@Override
	public void sendActivationEmail(String email, String activationToken) {
		MailContent mailContent = mailContentProvider.provideActivationMailContent(DEFAULT_MAIL_LOCALE, email, activationToken);
		EmailMessageDTO messageDTO = EmailMessageDTO.create(messageSource.getMessage("mailTemplate.activationMail.title", null, DEFAULT_MAIL_LOCALE), email).content(mailContent).build();
		mailSender.sendEmail(messageDTO);
	}
}
