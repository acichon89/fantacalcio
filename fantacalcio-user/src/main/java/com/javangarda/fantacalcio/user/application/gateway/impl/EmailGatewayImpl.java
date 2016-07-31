package com.javangarda.fantacalcio.user.application.gateway.impl;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.EmailMessageDTO;
import com.javangarda.fantacalcio.user.application.data.MailContent;
import com.javangarda.fantacalcio.user.application.gateway.EmailGateway;
import com.javangarda.fantacalcio.user.application.internal.MailContentProvider;
import com.javangarda.fantacalcio.user.application.internal.MailSender;
import com.javangarda.fantacalcio.util.i18n.SupportedLanguages;

@Component
public class EmailGatewayImpl implements EmailGateway {

	@Autowired
	private MailSender mailSender;
	@Autowired
	private MailContentProvider mailContentProvider;
	
	private String mailTitle;
	
	@Override
	public void sendActivationEmail(String email, String activationHash) {
		Map<String, String> args = buildArgumentsMap(email, activationHash);
		Locale locale = SupportedLanguages.ENGLISH.getLocale();
		MailContent mailContent = mailContentProvider.provideActivationMailContent( locale, args);
		EmailMessageDTO messageDTO = EmailMessageDTO.create(mailTitle, email)
				.contentPlain(mailContent.getContentPlain()).contentHtml(mailContent.getContentHtml()).build();
		mailSender.sendEmail(messageDTO);
				
	}
	
	private Map<String, String> buildArgumentsMap(String email, String activationHash) {
		Map<String, String> args = new HashMap<String, String>();
		args.put("email", email);
		args.put("token", activationHash);
		return args;
	}

}
