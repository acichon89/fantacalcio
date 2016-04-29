package com.javangarda.fantacalcio.football.domain.services.impl;

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

import com.javangarda.fantacalcio.football.application.FootballContext;
import com.javangarda.fantacalcio.football.contexts.FlywayIntegrationTestContext;
import com.javangarda.fantacalcio.football.contexts.SchemaVersionCleanTestExecutionListener;
import com.javangarda.fantacalcio.football.domain.events.DuplicateClubNameException;
import com.javangarda.fantacalcio.football.domain.model.Club;
import com.javangarda.fantacalcio.football.domain.repositories.ClubRepository;
import com.javangarda.fantacalcio.football.domain.services.ClubService;
import com.javangarda.fantacalcio.util.contexts.RootApplicationProfilesContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootApplicationProfilesContext.class, FootballContext.class, FlywayIntegrationTestContext.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class, SchemaVersionCleanTestExecutionListener.class })
@FlywayTest(invokeCleanDB=false, invokeBaselineDB=true, overrideLocations=true, locationsForMigrate="/TransactionalFootballServiceIT")
public class TransactionalFootballServiceIntegrationTest {

	@Autowired
	private ClubService clubService;
	@Autowired
	private ClubRepository clubRepository;
	
	@Test
	public void shouldStoreInRepoWhileCreatingClub() throws DuplicateClubNameException {
		Club juve = clubRepository.findOne("aaa-bbb");
		Assert.assertEquals("Juventus", juve.getName());
	}
	
	/*@Test(expected=DuplicateClubNameException.class)
	public void shouldThrowExceptionWhileStoringClubWithTheSameName() throws DuplicateClubNameException {
		clubService.createClub("Inter");
	}
	
	@Test
	public void shouldChangeNameOfUpdatedClub() throws DuplicateClubNameException {
		ClubDTO dto = ClubDTO.builder().active(true).name("AS Roma").id("waka-waka").build();
		clubService.updateClub(dto);
		Club roma = clubRepository.findOne("waka-waka");
		Assert.assertEquals("AS Roma", roma.getName());
	}
	
	@Test(expected=DuplicateClubNameException.class)
	public void shouldThrowErrorWhileFindAnotherClubWithSpecifiedName() throws DuplicateClubNameException {
		ClubDTO dto = ClubDTO.builder().active(true).name("Inter").id("waka-waka").build();
		clubService.updateClub(dto);
	}*/
}


