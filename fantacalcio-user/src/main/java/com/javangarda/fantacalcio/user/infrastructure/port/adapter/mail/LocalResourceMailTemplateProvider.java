package com.javangarda.fantacalcio.user.infrastructure.port.adapter.mail;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LocalResourceMailTemplateProvider implements MailTemplateProvider {

	@Value("${webapp.mainurl}")
	private String applicationUrl;
	
	private static final String ENCODING_TYPE = "UTF-8";
	
	@Override
	public String provideHtmlTemplate(String fileName) {
		try {
			Document doc = Jsoup.parse(loadResource(fileName), ENCODING_TYPE, applicationUrl);
			return doc.html();
		} catch (IOException e) {
			log.error("Error while retrieving template '"+fileName+"'", e);
			return "";
		}
	}

	@Override
	public String provideTextPlainTemplate(String fileName) {
		try {
			StringWriter writer = new StringWriter();
			IOUtils.copy(loadResource(fileName), writer, ENCODING_TYPE);
			return writer.toString();
		} catch (IOException e) {
			log.error("Error while retrieving template '"+fileName+"'", e);
			return "";
		}
	}
	
	private InputStream loadResource(String name) {
		return ClassLoader.getSystemClassLoader().getResourceAsStream(name);
	}

}
