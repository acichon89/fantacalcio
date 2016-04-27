package com.javangarda.fantacalcio.football.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value={
		FootballDomainContext.class, 
		FootballPersistenceContext.class, 
		FootballIntegrationContext.class
		})
public class FootballContext {

}
