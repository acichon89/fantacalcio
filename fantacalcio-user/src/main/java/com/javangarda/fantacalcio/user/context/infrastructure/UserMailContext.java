package com.javangarda.fantacalcio.user.context.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.javangarda.fantacalcio.user.application.internal.MailContentProvider;
import com.javangarda.fantacalcio.user.application.internal.MailSender;
import com.javangarda.fantacalcio.user.infrastructure.port.adapter.mail.LocalResourceMailTemplateProvider;
import com.javangarda.fantacalcio.user.infrastructure.port.adapter.mail.LoggerFakeMailSender;
import com.javangarda.fantacalcio.user.infrastructure.port.adapter.mail.MailTemplateProvider;
import com.javangarda.fantacalcio.user.infrastructure.port.adapter.mail.SystemPropMailContentProvider;
import com.javangarda.fantacalcio.util.profile.AppProfile;

@Configuration
@PropertySource(value="classpath:mails/mailTemplates.properties")
public class UserMailContext {

	@Bean
	@Profile(value={AppProfile.LOCALDEV_TEST, AppProfile.LOCALDEV_DEPLOY})
	public MailSender mailSender() {
		return new LoggerFakeMailSender();
	}
	
	@Bean
	public MailContentProvider mailContentProvider() {
		return new SystemPropMailContentProvider();
	}
	
	@Bean
	public MailTemplateProvider mailTemplateProvider() {
		return new LocalResourceMailTemplateProvider();
	}
}
