package com.javangarda.fantacalcio.user.infrastructure.port.adapter.mail;

public interface MailTemplateLoader {

	String loadHtml(String fileName);
	String loadPlain(String fileName);
}
