package com.javangarda.fantacalcio.user.context.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.javangarda.fantacalcio.user.application.internal.MailContentProvider;
import com.javangarda.fantacalcio.user.application.internal.MailSender;
import com.javangarda.fantacalcio.user.infrastructure.port.adapter.mail.LocalResourceMailTemplateLoader;
import com.javangarda.fantacalcio.user.infrastructure.port.adapter.mail.LoggerFakeMailSender;
import com.javangarda.fantacalcio.user.infrastructure.port.adapter.mail.MailTemplateLoader;
import com.javangarda.fantacalcio.user.infrastructure.port.adapter.mail.SystemPropMailContentProvider;
import com.javangarda.fantacalcio.util.profile.AppProfile;

@Configuration
public class UserMailContext {

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
