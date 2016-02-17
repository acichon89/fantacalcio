package com.javangarda.fantacalcio.gamerules.port.adapter.repositories;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit.FlywayHelperFactory;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.javangarda.fantacalcio.gamerules.domain.application.PersistenceContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Config.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class })
public class SimpleGameRuleRepositoryIntegrationTest {

	@Autowired
	private SimpleGameRuleRepository repository;
	
	@Test
	@FlywayTest(invokeCleanDB=false, invokeBaselineDB=true, locationsForMigrate="repos")
	public void test() {
		Assert.assertEquals(1, repository.count());
	}

}
@Configuration
@Import(value={PersistenceContext.class})
class Config {
	
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