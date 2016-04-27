package com.javangarda.fantacalcio.football.domain.gateways.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javangarda.fantacalcio.football.application.FootballContext;
import com.javangarda.fantacalcio.football.domain.data.ClubDTO;
import com.javangarda.fantacalcio.football.domain.events.DuplicateClubNameException;
import com.javangarda.fantacalcio.football.domain.services.ClubService;
import com.javangarda.fantacalcio.util.contexts.RootApplicationProfilesContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootApplicationProfilesContext.class, FootballContext.class})
public class EventDrivenFootballGatewayIntegrationTest {

	@Autowired
	private EventDrivenFootballGateway footballGateway;
	
	@Autowired
	@ReplaceWithMock
	private ClubService clubService;
	
	@Test
	public void test() throws DuplicateClubNameException {
		String id = footballGateway.createClub("Juventus");
		ClubDTO juveDTO = ClubDTO.builder().active(true).name("Juventus").id(id).build();
		footballGateway.updateClub(juveDTO);
	}
}
