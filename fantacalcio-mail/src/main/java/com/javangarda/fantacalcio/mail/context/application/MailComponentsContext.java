package com.javangarda.fantacalcio.mail.context.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages={"com.javangarda.fantacalcio.mail.application.gateway.impl",
		"com.javangarda.fantacalcio.mail.application.internal.impl"}, 
	excludeFilters = @ComponentScan.Filter(value = Configuration.class, type=FilterType.ANNOTATION))
public class MailComponentsContext {

}
