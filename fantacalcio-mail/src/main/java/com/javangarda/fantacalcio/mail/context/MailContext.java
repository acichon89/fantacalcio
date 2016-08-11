package com.javangarda.fantacalcio.mail.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.javangarda.fantacalcio.mail.context.application.MailApplicationContext;
import com.javangarda.fantacalcio.mail.context.infrastructure.MailInfrastructureContext;

@Configuration
@Import(value={MailApplicationContext.class, MailInfrastructureContext.class})
public class MailContext {

}
