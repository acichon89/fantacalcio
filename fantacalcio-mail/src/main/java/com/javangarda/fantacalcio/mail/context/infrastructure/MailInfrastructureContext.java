package com.javangarda.fantacalcio.mail.context.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.javangarda.fantacalcio.mail.application.internal.MailContentProvider;
import com.javangarda.fantacalcio.mail.application.internal.MailSender;
import com.javangarda.fantacalcio.mail.infrastructure.port.adapter.mail.LocalResourceMailTemplateLoader;
import com.javangarda.fantacalcio.mail.infrastructure.port.adapter.mail.LoggerFakeMailSender;
import com.javangarda.fantacalcio.mail.infrastructure.port.adapter.mail.MailTemplateLoader;
import com.javangarda.fantacalcio.mail.infrastructure.port.adapter.mail.SystemPropMailContentProvider;
import com.javangarda.fantacalcio.util.profile.AppProfile;

@Configuration
public class MailInfrastructureContext {

	@Configuration
	@Profile(value={AppProfile.LOCALDEV_TEST, AppProfile.LOCALDEV_DEPLOY})
	public static class FakeMailSenderConfig {
		
		@Bean
		public MailSender mailSender() {
			return new LoggerFakeMailSender();
		}
	}
	
	
	@Bean
	public MailContentProvider mailContentProvider() {
		return new SystemPropMailContentProvider();
	}
	
	@Bean
	public MailTemplateLoader mailTemplateLoader() {
		return new LocalResourceMailTemplateLoader();
	}
}
