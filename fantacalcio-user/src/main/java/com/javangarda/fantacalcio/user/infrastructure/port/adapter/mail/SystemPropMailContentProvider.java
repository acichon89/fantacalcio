package com.javangarda.fantacalcio.user.infrastructure.port.adapter.mail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.text.StrSubstitutor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.internal.MailContentProvider;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SystemPropMailContentProvider implements MailContentProvider{

	@Value("${mailTemplate.activationMail.plain}")
	private String activationMailContentPlainPattern;
	@Autowired
	private MessageSource messageSource;
	@Value("${webapp.mainurl}")
	private String applicationUrl;
	private static final String ACTIVATION_URL = "/activate?token=";
	
	@Override
	public String activationMailContentPlain(MailContentType contentType, Locale locale, Object... arguments) {
		Object[] finalArgs = createFinalArguments(arguments);
		switch (contentType) {
		case PLAIN:
			return messageSource.getMessage(activationMailContentPlainPattern, finalArgs, locale);
		case HTML:
			return provideMailContentHtml(finalArgs, locale);
		default:
			return null;
		}
	}
	
	private Object[] createFinalArguments(Object[] args){
		Object[] finalArgs = new Object[2];
		finalArgs[0] = args[0];
		finalArgs[1] = applicationUrl + ACTIVATION_URL + args[1];
		return finalArgs;
	}
	
	private String provideMailContentHtml(Object[] arguments, Locale locale) {
		try {
			String html = getHtmlTemplate("mails/mail_template.html", locale);
			return translateHtmlContent(html, arguments, locale);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String translateHtmlContent(String htmlContent, Object[] args, Locale locale) {
		Map<String, String> translatedReplacementFragments = createTranslatedReplacementFragments(args, locale);
		StrSubstitutor substritutor = new StrSubstitutor(translatedReplacementFragments);
		return substritutor.replace(htmlContent);
	}
	
	private String getHtmlTemplate(String path, Locale locale) {
		try {
			Document doc = Jsoup.parse(ClassLoader.getSystemClassLoader().getResourceAsStream(path), "UTF-8", "http://example.com/");
			return doc.html();
		} catch (IOException e) {
			log.error("Error while retrieving template '"+path+"'", e);
			return "";
		}
	}
	
	private Map<String, String> createTranslatedReplacementFragments(Object[] args, Locale locale){
		Map<String, String> map = new HashMap<>();
		map.put("welcomeText", messageSource.getMessage("mailTemplate.activationMail.html.welcomeText", new Object[]{args[0]}, locale));
		map.put("contentWithLink", messageSource.getMessage("mailTemplate.activationMail.html.contentWithLink", new Object[] {args[1]}, locale));
		map.put("ignoreMessageInfo", messageSource.getMessage("mailTemplate.activationMail.html.ignoreMessageInfo", null, locale));
		return map;
	}

}