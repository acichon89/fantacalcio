package com.javangarda.fantacalcio.mail.application.internal;

import java.util.Locale;

import com.javangarda.fantacalcio.mail.application.data.MailContent;

public interface MailContentProvider {
	
	MailContent provideActivationMailContent(Locale locale, Object... arguments);
}
