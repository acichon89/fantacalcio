package com.javangarda.fantacalcio.util.messages;

import java.util.Map;
import java.util.Set;

public class ValidationMessages {

	private Map<String, Set<Message>> messages;

	public boolean containsErrors() {
		return !messages.isEmpty();
	}
}
