package com.javangarda.fantacalcio.util.messages;

import lombok.Data;

@Data
public class Message {

	private Severity severity;
	private String key;
	private Object[] params;
}
