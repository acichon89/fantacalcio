package com.javangarda.fantacalcio.gamerules.port.adapter.repositories;

import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.javangarda.fantacalcio.testcontexts.IntegrationTestsContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=IntegrationTestsContext.class)
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