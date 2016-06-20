package com.javangarda.fantacalcio.web.contexts;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.javangarda.fantacalcio.football.application.FootballContext;
import com.javangarda.fantacalcio.user.context.UserContext;
import com.javangarda.fantacalcio.util.contexts.RootApplicationProfilesContext;

@Configuration
@Import(value={
		RootApplicationProfilesContext.class,
		WebSecurityContext.class,
		FootballContext.class,
		UserContext.class
		})
public class FantaCalcioApplicationContext {

}
