package com.javangarda.fantacalcio.user.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.javangarda.fantacalcio.user.context.application.UserApplicationContext;
import com.javangarda.fantacalcio.user.context.infrastructure.UserInfrastructureContext;

@Configuration
@Import(value={
		UserApplicationContext.class,
		UserInfrastructureContext.class
		})
public class UserContext {

}
