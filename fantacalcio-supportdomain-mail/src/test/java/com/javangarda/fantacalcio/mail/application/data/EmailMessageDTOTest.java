package com.javangarda.fantacalcio.mail.application.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.javangarda.fantacalcio.mail.application.data.EmailMessageDTO;
import com.javangarda.fantacalcio.mail.application.model.value.MailContent;


public class EmailMessageDTOTest {

	private MailContent mailContent;
	
	@Before
	public void init(){
		this.mailContent = new MailContent("<p>Hello!</p>", "Hello!");
	}
	
	@Test
	public void builderTest() {
		EmailMessageDTO dto = EmailMessageDTO.create("Hello World!", "admin@admin.pl").content(mailContent)
		.addBccRecipients("jan@kowalski.pl", "john@doe.pl").addCcRecipients("michael.jackson@music.org").build();
		Assert.assertEquals("Hello World!", dto.getTitle());
		Assert.assertEquals("admin@admin.pl", dto.getRecipientEmails().iterator().next());
		Assert.assertEquals(1, dto.getRecipientEmails().size());
		Assert.assertEquals(1, dto.getCcRecipientEmails().size());
		Assert.assertTrue(dto.getCcRecipientEmails().contains("michael.jackson@music.org"));
		Assert.assertTrue(dto.getBccRecipientEmails().contains("jan@kowalski.pl"));
		Assert.assertEquals(2, dto.getBccRecipientEmails().size());
	}

}
