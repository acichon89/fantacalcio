package com.javangarda.fantacalcio.user.application.gateway.impl;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;

import com.javangarda.fantacalcio.user.application.data.EmailMessageDTO;
import com.javangarda.fantacalcio.user.application.internal.MailContentProvider;
import com.javangarda.fantacalcio.user.application.internal.MailSender;
import com.javangarda.fantacalcio.user.application.model.value.MailContent;


@RunWith(MockitoJUnitRunner.class)
public class EmailGatewayImplTest {

	@Mock
	private MailSender mailSender;
	@Mock 
	private MailContentProvider mailContentProvider;
	@Mock
	private MessageSource messageSource;
	
	private Locale locale = new Locale("en_GB");
	
	@InjectMocks
	private EmailGatewayImpl emailGatewayImpl = Mockito.spy(new EmailGatewayImpl());
	
	@Test
	public void shouldFireMailSender() {
		//given:
		Mockito.when(messageSource.getMessage("mailTemplate.activationMail.title", null, locale)).thenReturn("Welcome to FantaCalcio!");
		MailContent mailContent = new MailContent("<p>Welcome, jan@kowalski.pl</p>", "Welcome jan@kowalski.pl");
		Mockito.when(mailContentProvider.provideActivationMailContent(locale, "jan@kowalski.pl", "abcd")).thenReturn(mailContent);
		//when:
		emailGatewayImpl.sendActivationEmail("jan@kowalski.pl", "abcd");
		//then:
		ArgumentCaptor<EmailMessageDTO> argument = ArgumentCaptor.forClass(EmailMessageDTO.class);
		Mockito.verify(mailSender).sendEmail(argument.capture());
		Assert.assertEquals("Welcome jan@kowalski.pl", argument.getValue().getMailContent().getContentPlain());
		Assert.assertEquals("jan@kowalski.pl", argument.getValue().getRecipientEmails().iterator().next());
	}

}
