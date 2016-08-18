package com.javangarda.fantacalcio.util.testsupport;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.test.junit.FlywayHelperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayIntegrationTestContext {
	
	@Bean
	public FlywayHelperFactory flywayHelperFactory(){
		FlywayHelperFactory factory = new FlywayHelperFactory();
		Properties flywayProperties = new Properties();
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
