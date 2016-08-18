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

	@Override
	public int hashCode() {
		return this.key.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || !(object instanceof Message) ){
			return false;
		}
		Message other = (Message) object;
		return other.key.equals(this.key);
	}

	
}
