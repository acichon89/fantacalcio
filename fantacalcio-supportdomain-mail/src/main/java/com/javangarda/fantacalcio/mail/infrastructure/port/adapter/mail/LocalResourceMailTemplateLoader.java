package com.javangarda.fantacalcio.mail.infrastructure.port.adapter.mail;

import java.io.InputStream;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LocalResourceMailTemplateLoader implements MailTemplateLoader {


	private static final String ENCODING_TYPE = "UTF-8";
	
	@Override
	public String load(String fileName) {
		Scanner scanner = new Scanner(loadResource(fileName), ENCODING_TYPE);
		String content = scanner.hasNext() ? scanner.next() : "";
		scanner.close();
		return content;
	}
	
	
	private InputStream loadResource(String name) {
		return ClassLoader.getSystemClassLoader().getResourceAsStream(name);
	}

}
