package com.javangarda.fantacalcio.user.application.gateway.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.EmailMessageDTO;
import com.javangarda.fantacalcio.user.application.gateway.EmailGateway;
import com.javangarda.fantacalcio.user.application.internal.MailContentProvider;
import com.javangarda.fantacalcio.user.application.internal.MailSender;
import com.javangarda.fantacalcio.user.application.internal.MailContentProvider.MailContentType;

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
		String contentPlain = mailContentProvider.activationMailContentPlain(MailContentType.PLAIN, args);
		String contentHtml = mailContentProvider.activationMailContentPlain(MailContentType.HTML, args);
		EmailMessageDTO messageDTO = EmailMessageDTO.create(mailTitle, email)
				.contentPlain(contentPlain).contentHtml(contentHtml).build();
		mailSender.sendEmail(messageDTO);
				
	}
	
	private Map<String, String> buildArgumentsMap(String email, String activationHash) {
		Map<String, String> args = new HashMap<String, String>();
		args.put("email", email);
		args.put("token", activationHash);
		return args;
	}

}
