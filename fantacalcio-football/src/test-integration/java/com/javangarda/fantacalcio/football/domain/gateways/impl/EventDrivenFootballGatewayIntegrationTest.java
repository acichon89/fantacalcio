package com.javangarda.fantacalcio.football.domain.gateways.impl;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.BridgeFrom;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javangarda.fantacalcio.football.application.FootballDomainContext;
import com.javangarda.fantacalcio.football.application.FootballIntegrationContext;
import com.javangarda.fantacalcio.football.contexts.DisableAutowireRequireInitializer;
import com.javangarda.fantacalcio.football.domain.data.ClubDTO;
import com.javangarda.fantacalcio.football.domain.data.CreatingPlayerDTO;
import com.javangarda.fantacalcio.football.domain.data.PlayerTransferDTO;
import com.javangarda.fantacalcio.football.domain.data.UpdatingPlayerDTO;
import com.javangarda.fantacalcio.football.domain.events.DuplicateClubNameException;
import com.javangarda.fantacalcio.football.domain.services.ClubService;
import com.javangarda.fantacalcio.football.domain.services.PlayerService;
import com.javangarda.fantacalcio.football.domain.values.PlayerPosition;
import com.javangarda.fantacalcio.util.contexts.RootApplicationProfilesContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=EventDrivenFootballGatewayIntegrationTestContext.class, initializers={DisableAutowireRequireInitializer.class})
public class EventDrivenFootballGatewayIntegrationTest {
	
	@Autowired
	private EventDrivenFootballGateway gateway;
	@Autowired
	private ClubService clubService;
	@Autowired
	private PlayerService playerService;
	@Autowired
	private QueueChannel updateClubTestQueue;
	@Autowired
	private QueueChannel updatePlayerTestQueue;
	@Autowired
	private QueueChannel transferPlayerTestQueue;
	
	@Test
	public void shouldDelegateToServiceWhileCreatingClub() throws DuplicateClubNameException {
		//given:
		//when:
		String value = gateway.createClub("Juve");
		//then:
		Assert.assertEquals("Abc", value);
		Mockito.verify(clubService, Mockito.times(1)).createClub("Juve");
	}
	
	@Test
	public void shouldSendEventAndDelegateToServiceWhileUpdatingClub() throws DuplicateClubNameException {
		//given:
		ClubDTO clubDTO = ClubDTO.builder().active(true).name("AC Milan").id("aaa-bbb").build();
		//when:
		gateway.updateClub(clubDTO);
		//then:
		Message<?> message = updateClubTestQueue.receive(0);
		Assert.assertNotNull(message);
		ClubDTO payload = (ClubDTO) message.getPayload();
		Assert.assertEquals(clubDTO, payload);
		Mockito.verify(clubService, Mockito.times(1)).updateClub(clubDTO);
	}
	
	@Test(expected=DuplicateClubNameException.class)
	public void shouldThrowExceptionWhileUpdatingClub() throws DuplicateClubNameException {
		//given:
		//when:
		gateway.createClub("AC Milan");
		//then:
	}
	
	@Test
	public void shouldDelegateToServiceWhileCreatingPlayer() {
		//given:
		CreatingPlayerDTO dto = CreatingPlayerDTO.builder().clubId("aaa-bbb").active(true).dateOfBirth(DateTime.now()).fullName("Ricardo Montolivo").position(PlayerPosition.MIDFIELDER).build();
		//when:
		String id = gateway.createPlayer(dto);
		//then:
		Assert.assertEquals("Xyz", id);
		Mockito.verify(playerService, Mockito.times(1)).createPlayer(dto);
	}
	
	@Test
	public void shouldSendEventAndDelegateToServiceWhileUpdatingPlayer() throws DuplicateClubNameException {
		//given:
		UpdatingPlayerDTO dto = UpdatingPlayerDTO.builder().dateOfBirth(DateTime.now()).fullName("Clauido Marchisio").position(PlayerPosition.MIDFIELDER).build();
		//when:
		gateway.updatePlayer(dto);
		//then:
		Message<?> message = updatePlayerTestQueue.receive(0);
		Assert.assertNotNull(message);
		UpdatingPlayerDTO payload = (UpdatingPlayerDTO) message.getPayload();
		Assert.assertEquals(dto, payload);
		Mockito.verify(playerService, Mockito.times(1)).updatePlayer(dto);
	}
	
	@Test
	public void shouldSendEventAndDelegateToServiceWhileTransferingPlayer() {
		//given:
		PlayerTransferDTO dto = PlayerTransferDTO.builder().clubId("aaa-xxx").playerId("www-yyy").build();
		//when:
		gateway.transferPlayer(dto);
		//then:
		Message<?> message = transferPlayerTestQueue.receive(0);
		Assert.assertNotNull(message);
		PlayerTransferDTO payload = (PlayerTransferDTO) message.getPayload();
		Assert.assertEquals(dto, payload);
		Mockito.verify(playerService, Mockito.times(1)).transferPlayer(dto);
	}
}

@Configuration
@Import(value={FootballDomainContext.class,FootballIntegrationContext.class, RootApplicationProfilesContext.class})
class EventDrivenFootballGatewayIntegrationTestContext {
	
	@Bean
	public ClubService clubService() throws DuplicateClubNameException{
		ClubService clubService = Mockito.mock(ClubService.class);
		Mockito.when(clubService.createClub("Juve")).thenReturn("Abc");
		Mockito.when(clubService.createClub("AC Milan")).thenThrow(DuplicateClubNameException.class);
		return clubService;
	}
	
	@Bean
	public PlayerService playerService() {
		PlayerService playerService = Mockito.mock(PlayerService.class);
		Mockito.when(playerService.createPlayer(Mockito.any(CreatingPlayerDTO.class))).thenReturn("Xyz");
		return playerService;
	}
	
	@Bean
	@BridgeFrom(value="updateClubChannel")
	public QueueChannel updateClubTestQueue(){
		return new QueueChannel(1);
	}
	
	@Bean
	@BridgeFrom(value="updatePlayerChannel")
	public QueueChannel updatePlayerTestQueue(){
		return new QueueChannel(1);
	}
	
	@Bean
	@BridgeFrom(value="playerTransferChannel")
	public QueueChannel transferPlayerTestQueue(){
		return new QueueChannel(1);
	}
	
}
