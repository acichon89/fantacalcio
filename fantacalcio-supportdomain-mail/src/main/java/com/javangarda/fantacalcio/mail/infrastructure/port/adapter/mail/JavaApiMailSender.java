package com.javangarda.fantacalcio.mail.infrastructure.port.adapter.mail;

import com.javangarda.fantacalcio.mail.application.data.EmailMessageDTO;
import com.javangarda.fantacalcio.mail.application.internal.MailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JavaApiMailSender implements MailSender {

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendEmail(EmailMessageDTO dto) {
		try {
			MimeMessageHelper message = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
			message.addTo(dto.getRecipient());
			message.setSubject(dto.getTitle());
			message.setText(dto.getMailContent().getContentPlain(), dto.getMailContent().getContentHtml());
			javaMailSender.send(message.getMimeMessage());
		} catch (Exception e) {
			log.error("Error at java api mail sender", e);
		}
	}
}
