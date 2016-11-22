package com.javangarda.fantacalcio.mail.application.internal;

import com.javangarda.fantacalcio.mail.application.data.EmailMessageDTO;

public interface MailSender {

	void sendEmail(EmailMessageDTO dto);
}