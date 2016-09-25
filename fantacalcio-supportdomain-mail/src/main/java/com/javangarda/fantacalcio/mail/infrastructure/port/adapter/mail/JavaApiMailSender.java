package com.javangarda.fantacalcio.mail.infrastructure.port.adapter.mail;

import javax.mail.MessagingException;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.mail.application.data.EmailMessageDTO;
import com.javangarda.fantacalcio.mail.application.internal.MailSender;

@Component
public class JavaApiMailSender implements MailSender {

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendEmail(EmailMessageDTO dto) {
		try {
			MimeMessageHelper message = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
			for(String email : dto.getRecipientEmails()){
				message.addTo(email);
			}
			message.setSubject(dto.getTitle());
			if(CollectionUtils.isNotEmpty(dto.getCcRecipientEmails())){
				message.setCc(dto.getCcRecipientEmails().toArray(new String[dto.getCcRecipientEmails().size()]));
			}
			if(CollectionUtils.isNotEmpty(dto.getBccRecipientEmails())){
				message.setBcc(dto.getBccRecipientEmails().toArray(new String[dto.getBccRecipientEmails().size()]));
			}
			message.setText(dto.getMailContent().getContentPlain(), dto.getMailContent().getContentHtml());
			javaMailSender.send(message.getMimeMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
