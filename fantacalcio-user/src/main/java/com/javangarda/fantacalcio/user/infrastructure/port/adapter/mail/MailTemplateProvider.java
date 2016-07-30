package com.javangarda.fantacalcio.user.infrastructure.port.adapter.mail;

public interface MailTemplateProvider {

	String provideHtmlTemplate(String fileName);
	String provideTextPlainTemplate(String fileName);
}
