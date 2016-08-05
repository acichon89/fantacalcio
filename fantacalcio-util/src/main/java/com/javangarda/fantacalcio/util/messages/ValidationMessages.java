package com.javangarda.fantacalcio.util.messages;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import lombok.Getter;

public class ValidationMessages {

	@Getter
	private Map<String, Set<Message>> messages;
	
	private ValidationMessages() {
		this.messages = new HashMap<String, Set<Message>>();
	}
	
	public static ValidationMessages createEmpty() {
		return new ValidationMessages();
	}

	public boolean isEmpty() {
		return messages.isEmpty();
	}
	
	public void add(String field, Message... messages) {
		Collection<Message> msgs = Arrays.asList(messages);
		if(this.messages.containsKey(field)){
			this.messages.get(field).addAll(msgs);
		} else this.messages.put(field, new HashSet<>(msgs));
	}
	
	public void merge(ValidationMessages other) {
		Iterator<Entry<String, Set<Message>>> iterator = other.getMessages().entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, Set<Message>> entry = iterator.next();
			add(entry.getKey(), entry.getValue().toArray(new Message[entry.getValue().size()]));
		}
	}
}
