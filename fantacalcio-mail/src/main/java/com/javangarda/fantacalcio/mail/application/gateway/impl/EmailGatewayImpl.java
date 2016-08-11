package com.javangarda.fantacalcio.mail.application.gateway.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.mail.application.data.EmailMessageDTO;
import com.javangarda.fantacalcio.mail.application.gateway.EmailGateway;
import com.javangarda.fantacalcio.mail.application.internal.MailContentProvider;
import com.javangarda.fantacalcio.mail.application.internal.MailSender;
import com.javangarda.fantacalcio.mail.application.model.value.MailContent;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailGatewayImpl implements EmailGateway {

	@Autowired
	private MailSender mailSender;
	@Autowired
	private MailContentProvider mailContentProvider;
	@Setter
	private MessageSource messageSource;
	
	@ServiceActivator(inputChannel="sendingConfirmationEmailChannel")
	@Override
	public void sendActivationEmail(@Payload String email, @Header("activationToken") String activationToken) {
		log.debug("Thread = {} @EmailGatewayImpl::sendActivationEmail, email={}, activationToken={}", Thread.currentThread().getName(), email, activationToken);
		MailContent mailContent = mailContentProvider.provideActivationMailContent(DEFAULT_MAIL_LOCALE, email, activationToken);
		EmailMessageDTO messageDTO = EmailMessageDTO.create(messageSource.getMessage("mailTemplate.activationMail.title", null, DEFAULT_MAIL_LOCALE), email).content(mailContent).build();
		mailSender.sendEmail(messageDTO);
	}
}
