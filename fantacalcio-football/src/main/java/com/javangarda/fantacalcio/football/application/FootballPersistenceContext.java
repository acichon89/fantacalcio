package com.javangarda.fantacalcio.football.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.javangarda.fantacalcio.util.contexts.RootPersistenceContext;
import com.javangarda.fantacalcio.util.contexts.RootTransactionalContext;

@Configuration
@Import({RootPersistenceContext.class, RootTransactionalContext.class})
@EnableJpaRepositories(basePackages = {"com.javangarda.fantacalcio.football.domain.repositories"})
public class FootballPersistenceContext {

	
}
