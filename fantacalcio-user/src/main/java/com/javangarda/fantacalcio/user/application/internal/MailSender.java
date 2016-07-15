package com.javangarda.fantacalcio.user.application.internal;

import com.javangarda.fantacalcio.user.application.data.EmailMessageDTO;

public interface MailSender {

	void sendEmail(EmailMessageDTO dto);
}
