package com.javangarda.fantacalcio.mail.application.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class EmailMessageDTO {

	@Getter
	private String title;
	@Getter
	private String recipient;
	@Getter
	private MailContent mailContent;
	
}
