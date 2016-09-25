package com.javangarda.fantacalcio.mail.application.model.value;

import lombok.Getter;
import lombok.ToString;

@ToString
public class MailContent {

	@Getter
	private String contentHtml;
	@Getter 
	private String contentPlain;
	
	public MailContent(String html, String plain){
		this.contentHtml=html;
		this.contentPlain=plain;
	}
}
