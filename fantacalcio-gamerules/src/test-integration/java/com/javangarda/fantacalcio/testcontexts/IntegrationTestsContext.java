package com.javangarda.fantacalcio.testcontexts;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.test.junit.FlywayHelperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.javangarda.fantacalcio.gamerules.domain.application.PersistenceContext;

@Configuration
@Import(value={PersistenceContext.class})
public class IntegrationTestsContext {
	
	@Bean
	public FlywayHelperFactory flywayHelperFactory(){
		FlywayHelperFactory factory = new FlywayHelperFactory();
		Properties flywayProperties = new Properties();
		flywayProperties.put("flyway.locations", "/repos");
		factory.setFlywayProperties(flywayProperties);
		return factory;
	}
	
	@Bean
	public Flyway flyway(FlywayHelperFactory factory, DataSource dataSource) throws SQLException{
		Flyway flyway = factory.createFlyway();
		flyway.setDataSource(dataSource);
		return flyway;
	}
}