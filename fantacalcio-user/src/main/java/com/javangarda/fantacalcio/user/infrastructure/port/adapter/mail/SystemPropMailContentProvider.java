package com.javangarda.fantacalcio.user.infrastructure.port.adapter.mail;

import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.internal.MailContentProvider;
import com.javangarda.fantacalcio.util.messages.MapFormat;

@Component
public class SystemPropMailContentProvider implements MailContentProvider{

	@Value("${mailTemplate.activationMail.plain}")
	private String activationMailContentPlainPattern;
	
	@Override
	public String activationMailContentPlain(MailContentType contentType, Map<String, String> arguments) {
		switch (contentType) {
		case PLAIN:
			return provideMailContentPlain(activationMailContentPlainPattern, arguments);
		case HTML:
			return provideMailContentHtml("activationMailTemplate.html", arguments);
		default:
			return null;
		}
	}
	
	private String provideMailContentPlain(String key, Map<String, String> arguments) {
		return MapFormat.format(key, arguments);
	}
	
	private String provideMailContentHtml(String path, Map<String, String> arguments) {
		try {
			Document doc = Jsoup.parse(ClassLoader.getSystemClassLoader().getResourceAsStream("mails/mail_template.html"), "UTF-8", "http://example.com/");
			String html = doc.html();
			return MapFormat.format(html, arguments);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
