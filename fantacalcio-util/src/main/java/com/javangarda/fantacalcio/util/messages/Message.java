package com.javangarda.fantacalcio.util.messages;

import lombok.Getter;

public class Message {

	@Getter
	private Severity severity;
	@Getter
	private String key;
	@Getter
	private Object[] params;
	
	private Message(Severity severity, String key, Object... params){
		this.severity=severity;
		this.key=key;
		this.params=params;
	}
	
	public static Message of(Severity severity, String key, Object... params) {
		return new Message(severity, key, params);
	}
}
