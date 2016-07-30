package com.javangarda.fantacalcio.user.application.internal;

import java.util.Locale;

public interface MailContentProvider {
	
	enum MailContentType {PLAIN, HTML};

	String provideActivationMailContent(MailContentType contentType, Locale locale, Object... arguments);
}
