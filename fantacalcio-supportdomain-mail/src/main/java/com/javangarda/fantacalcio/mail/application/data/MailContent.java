package com.javangarda.fantacalcio.mail.application.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class MailContent {

	@Getter
	private String contentHtml;
	@Getter 
	private String contentPlain;
	
}
