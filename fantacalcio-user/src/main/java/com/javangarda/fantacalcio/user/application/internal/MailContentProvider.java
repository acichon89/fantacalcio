package com.javangarda.fantacalcio.user.application.internal;

import java.util.Locale;

import com.javangarda.fantacalcio.user.application.model.value.MailContent;

public interface MailContentProvider {
	
	MailContent provideActivationMailContent(Locale locale, Object... arguments);
}
