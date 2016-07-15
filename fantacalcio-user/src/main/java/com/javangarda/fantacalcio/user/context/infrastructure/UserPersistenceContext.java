package com.javangarda.fantacalcio.user.context.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.javangarda.fantacalcio.util.contexts.RootPersistenceContext;
import com.javangarda.fantacalcio.util.contexts.RootTransactionalContext;

@Configuration
@Import({RootPersistenceContext.class, RootTransactionalContext.class})
@EnableJpaRepositories(basePackages = {"com.javangarda.fantacalcio.user.application.internal"})
public class UserPersistenceContext {

	
}
