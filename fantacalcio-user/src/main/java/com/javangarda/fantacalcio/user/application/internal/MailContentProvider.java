package com.javangarda.fantacalcio.user.application.internal;

import java.util.Map;

public interface MailContentProvider {
	
	enum MailContentType {PLAIN, HTML};

	String activationMailContentPlain(MailContentType contentType, Map<String, String> arguments);
}
