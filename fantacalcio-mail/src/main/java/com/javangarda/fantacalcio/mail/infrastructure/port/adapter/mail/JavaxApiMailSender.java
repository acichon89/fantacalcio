package com.javangarda.fantacalcio.mail.infrastructure.port.adapter.mail;

import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.mail.application.data.EmailMessageDTO;
import com.javangarda.fantacalcio.mail.application.internal.MailSender;

@Component
public class JavaxApiMailSender implements MailSender {

	@Override
	public void sendEmail(EmailMessageDTO dto) {
		// TODO Auto-generated method stub
		
	}

	


}
