package com.javangarda.fantacalcio.web.contexts;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.javangarda.fantacalcio.football.application.FootballContext;
import com.javangarda.fantacalcio.mail.context.MailContext;
import com.javangarda.fantacalcio.user.context.UserContext;
import com.javangarda.fantacalcio.util.contexts.RootApplicationProfilesContext;
import com.javangarda.fantacalcio.util.contexts.RootI18nContext;

@Configuration
@Import(value={
		RootApplicationProfilesContext.class,
		RootI18nContext.class, 
		WebSecurityContext.class,
		FootballContext.class,
		UserContext.class,
		MailContext.class
		})
public class FantaCalcioApplicationContext {

}
