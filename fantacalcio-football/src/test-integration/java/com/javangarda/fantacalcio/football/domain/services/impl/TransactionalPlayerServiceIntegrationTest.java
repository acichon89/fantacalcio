package com.javangarda.fantacalcio.football.domain.services.impl;

import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.javangarda.fantacalcio.football.application.FootballContext;
import com.javangarda.fantacalcio.football.domain.FootballSchemaVersionCleanTestExecutionListener;
import com.javangarda.fantacalcio.football.domain.data.CreatingPlayerDTO;
import com.javangarda.fantacalcio.football.domain.data.PlayerTransferDTO;
import com.javangarda.fantacalcio.football.domain.data.UpdatingPlayerDTO;
import com.javangarda.fantacalcio.football.domain.model.Player;
import com.javangarda.fantacalcio.football.domain.repositories.PlayerRepository;
import com.javangarda.fantacalcio.football.domain.services.PlayerService;
import com.javangarda.fantacalcio.football.domain.values.PlayerPosition;
import com.javangarda.fantacalcio.util.contexts.RootApplicationProfilesContext;
import com.javangarda.fantacalcio.util.testsupport.FlywayIntegrationTestContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootApplicationProfilesContext.class, FootballContext.class, FlywayIntegrationTestContext.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class, FootballSchemaVersionCleanTestExecutionListener.class, TransactionalTestExecutionListener.class})
@FlywayTest(invokeCleanDB=false, invokeBaselineDB=true, overrideLocations=true, locationsForMigrate="/TransactionalPlayerServiceIT")
@Transactional
public class TransactionalPlayerServiceIntegrationTest {

	@Autowired
	private PlayerService playerService;
	@Autowired
	private PlayerRepository playerRepository;
	
	private DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
	
	@Test
	public void shouldStorePlayerWhileCreating() {
		//given:
		CreatingPlayerDTO dto = CreatingPlayerDTO.builder().active(true).clubId("aaa-bbb").dateOfBirth(null)
				.fullName("Paul Pogba").position(PlayerPosition.MIDFIELDER).build();
		//when:
		String id = playerService.createPlayer(dto);
		//then:
		Player paulPogba = playerRepository.findOne(id);
		Assert.assertEquals(dto.getFullName(), paulPogba.getFullName());
		Assert.assertEquals("Juventus", paulPogba.getClub().getName());
	}
	
	@Test
	public void shouldChangePersistedPlayerDataWhileUpdating() {
		//given:
		UpdatingPlayerDTO dto = UpdatingPlayerDTO.builder().fullName("Gigi Buffon").playerId("123-999").
				dateOfBirth(dtf.parseDateTime("1978-01-28")).position(PlayerPosition.MIDFIELDER)
				.build();
		//when:
		playerService.updatePlayer(dto);
		//then:
		Player gigi = playerRepository.findOne(dto.getPlayerId());
		Assert.assertEquals(dto.getFullName(), gigi.getFullName());
		Assert.assertEquals(dto.getPosition(), gigi.getPosition());
	}
	
	@Test
	public void shouldChangeClubOfPersistedPlayerWHileTransfering() {
		//given:
		PlayerTransferDTO dto = PlayerTransferDTO.builder().clubId("aaa-ccc").playerId("123-999").build();
		//when:
		playerService.transferPlayer(dto);
		//then:
		Player gigi = playerRepository.findOne(dto.getPlayerId());
		Assert.assertEquals("AC Milan", gigi.getClub().getName());
	}
}
