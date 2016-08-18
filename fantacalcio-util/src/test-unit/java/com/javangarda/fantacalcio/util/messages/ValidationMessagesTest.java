package com.javangarda.fantacalcio.util.messages;

import org.junit.Assert;
import org.junit.Test;

public class ValidationMessagesTest {

	
	@Test
	public void shouldNotBeEmptyWhenAddSth() {
		ValidationMessages messages = ValidationMessages.createEmpty();
		Assert.assertTrue(messages.isEmpty());
		messages.add("name", Message.of(Severity.WARNING, "name.empty"));
		Assert.assertFalse(messages.isEmpty());
	}

	@Test
	public void shouldMergeWhenAddTheSame(){
		ValidationMessages messages = ValidationMessages.createEmpty();
		messages.add("name", Message.of(Severity.WARNING, "name.empty"));
		Assert.assertEquals(1, messages.getMessages().size());
		Assert.assertEquals(1, messages.getMessages().get("name").size());
		messages.add("name", Message.of(Severity.WARNING, "name.empty"));
		Assert.assertEquals(1, messages.getMessages().size());
		Assert.assertEquals(1, messages.getMessages().get("name").size());
		messages.add("name", Message.of(Severity.WARNING, "name.toolong"));
		Assert.assertEquals(1, messages.getMessages().size());
		Assert.assertEquals(2, messages.getMessages().get("name").size());
		messages.add("name", Message.of(Severity.WARNING, "name.toolong", new Object[] {1}));
		Assert.assertEquals(1, messages.getMessages().size());
		Assert.assertEquals(2, messages.getMessages().get("name").size());
	}
	
	@Test
	public void mergeTest() {
		ValidationMessages passwordMessages = ValidationMessages.createEmpty();
		passwordMessages.add("name", Message.of(Severity.WARNING, "name.empty"));
		
		ValidationMessages dateMessages = ValidationMessages.createEmpty();
		dateMessages.add("date", Message.of(Severity.WARNING, "date.empty", new Object[] {1}));
		dateMessages.add("name", Message.of(Severity.WARNING, "name.empty"));
		
		passwordMessages.merge(dateMessages);
		
		Assert.assertEquals(2, passwordMessages.getMessages().size());
		Assert.assertEquals(1, passwordMessages.getMessages().get("name").size());
	}
}
