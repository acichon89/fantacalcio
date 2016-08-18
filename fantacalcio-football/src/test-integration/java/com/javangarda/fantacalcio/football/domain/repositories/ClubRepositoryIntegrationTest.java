package com.javangarda.fantacalcio.football.domain.repositories;


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

import com.javangarda.fantacalcio.football.application.FootballPersistenceContext;
import com.javangarda.fantacalcio.football.domain.FootballSchemaVersionCleanTestExecutionListener;
import com.javangarda.fantacalcio.util.contexts.RootApplicationProfilesContext;
import com.javangarda.fantacalcio.util.testsupport.FlywayIntegrationTestContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={FootballPersistenceContext.class, RootApplicationProfilesContext.class, FlywayIntegrationTestContext.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class, FootballSchemaVersionCleanTestExecutionListener.class })
@FlywayTest(invokeCleanDB=false, invokeBaselineDB=true, overrideLocations=true, locationsForMigrate="/ClubRepositoryIT")
public class ClubRepositoryIntegrationTest {

	@Autowired
	private ClubRepository clubRepository;
	
	@Test
	public void countByNameTest() {
		Assert.assertEquals(1, clubRepository.countByName("Juventus"));
		Assert.assertEquals(0, clubRepository.countByName("Internazionale"));
	}
	
	@Test
	public void countByNameAndNotId() {
		Assert.assertEquals(0, clubRepository.countByNameAndNotId("Juventus", "aaa-bbb"));
		Assert.assertEquals(1, clubRepository.countByNameAndNotId("Juventus", "aac-bbb"));
	}
}
