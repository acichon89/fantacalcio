package com.javangarda.fantacalcio.mail.infrastructure.port.adapter.mail;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.mail.application.internal.MailContentProvider;
import com.javangarda.fantacalcio.mail.application.model.value.MailContent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SystemPropMailContentProvider implements MailContentProvider{

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private MailTemplateLoader mailTemplateLoader;
	@Value("${fantacalcio.webapp.mainurl}")
	private String applicationUrl;
	private static final String ACTIVATION_URL = "/activate?token=";
	
	@Override
	public MailContent provideActivationMailContent(Locale locale, Object... arguments) {
		Object[] finalArgs = createActivationMailContentFinalArguments(arguments);
		String plain = provideMailContentPlain(finalArgs, locale);
		String html = provideMailContentHtml(finalArgs, locale);
		return new MailContent(html, plain);
	}
	
	private Object[] createActivationMailContentFinalArguments(Object[] args){
		Object[] finalArgs = new Object[2];
		finalArgs[0] = args[0];
		finalArgs[1] = applicationUrl + ACTIVATION_URL + args[1];
		return finalArgs;
	}
	
	private String provideMailContentPlain(Object[] arguments, Locale locale) {
		String txtPlain = mailTemplateLoader.load("mails/activation_mail_template.txt");
		return translateContent(txtPlain, arguments, locale);
	}
	
	private String provideMailContentHtml(Object[] arguments, Locale locale) {
		String html = mailTemplateLoader.load("mails/activation_mail_template.html");
		return translateContent(html, arguments, locale);
	}
	
	private String translateContent(String htmlContent, Object[] args, Locale locale) {
		Map<String, String> translatedReplacementFragments = createTranslatedReplacementFragments(args, locale);
		StrSubstitutor substritutor = new StrSubstitutor(translatedReplacementFragments);
		return substritutor.replace(htmlContent);
	}
	
	private Map<String, String> createTranslatedReplacementFragments(Object[] args, Locale locale){
		Map<String, String> map = new HashMap<>();
		map.put("welcomeText", messageSource.getMessage("activationMail.content.welcomeText", new Object[]{args[0]}, locale));
		map.put("contentWithLink", messageSource.getMessage("activationMail.content.contentWithLink", new Object[] {args[1]}, locale));
		map.put("ignoreMessageInfo", messageSource.getMessage("activationMail.content.ignoreMessageInfo", null, locale));
		return map;
	}

}
