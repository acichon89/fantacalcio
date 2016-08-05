package com.javangarda.fantacalcio.user.application.model.value;

import lombok.Getter;

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
