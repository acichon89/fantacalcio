package com.javangarda.fantacalcio.user.infrastructure.port.adapter.mail;

import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.EmailMessageDTO;
import com.javangarda.fantacalcio.user.application.internal.MailSender;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoggerFakeMailSender implements MailSender {
	
	@Override	
	public void sendEmail(EmailMessageDTO dto) {
		log.debug("@@@ | MAIL SENDER | @@@@ to :"+dto.getRecipientEmails()+" | cc: "+dto.getCcRecipientEmails() +
				" | title: "+dto.getTitle()+" | contentPlain: "+dto.getContentPlain()+" | contentHtml: "+dto.getContentHtml());
	}

}
