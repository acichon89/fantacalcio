package com.javangarda.fantacalcio.mail.infrastructure.port.adapter.mail;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LocalResourceMailTemplateLoader implements MailTemplateLoader {

	@Value("${fantacalcio.webapp.mainurl}")
	private String applicationUrl;
	
	private static final String ENCODING_TYPE = "UTF-8";
	private static String[] HTML_EXTENSIONS = {"html", "htm"};
	private static String[] TEXTPLAIN_EXTENSIONS = {"txt"};
	
	@Override
	public String load(String fileName) {
		String extension = FilenameUtils.getExtension(fileName);
		if(ArrayUtils.contains(HTML_EXTENSIONS, extension)){
			return loadHtml(fileName);
		} else if(ArrayUtils.contains(TEXTPLAIN_EXTENSIONS, extension)){
			return loadPlain(fileName);
		} else throw new IllegalArgumentException("Unknown filename extension of file '"+fileName+"'");
	}
	
	private String loadHtml(String fileName) {
		try {
			Document doc = Jsoup.parse(loadResource(fileName), ENCODING_TYPE, applicationUrl);
			return doc.html();
		} catch (IOException e) {
			log.error("Error while retrieving template '"+fileName+"'", e);
			return "";
		}
	}

	private String loadPlain(String fileName) {
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
