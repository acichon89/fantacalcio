package com.javangarda.fantacalcio.football.domain.gateways.impl;

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
import com.javangarda.fantacalcio.football.domain.events.DuplicateClubNameException;
import com.javangarda.fantacalcio.football.domain.services.ClubService;
import com.javangarda.fantacalcio.util.contexts.RootApplicationProfilesContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=EventDrivenFootballGatewayIntegrationTestContext.class, initializers={DisableAutowireRequireInitializer.class})
public class EventDrivenFootballGatewayIntegrationTest {
	
	@Autowired
	private EventDrivenFootballGateway gateway;
	@Autowired
	private ClubService clubService;
	@Autowired
	private QueueChannel testChannel;
	
	@Test
	public void shouldDelegateToServiceWhileCreatingClub() throws DuplicateClubNameException {
		String value = gateway.createClub("Juve");
		Assert.assertEquals("Abc", value);
		Mockito.verify(clubService, Mockito.times(1)).createClub("Juve");
	}
	
	@Test
	public void shouldSendEventWhileUpdatingClub() throws DuplicateClubNameException {
		ClubDTO clubDTO = ClubDTO.builder().active(true).name("AC Milan").id("aaa-bbb").build();
		gateway.updateClub(clubDTO);
		Message<?> message = testChannel.receive(0);
		Assert.assertNotNull(message);
		ClubDTO payload = (ClubDTO) message.getPayload();
		Assert.assertEquals(clubDTO, payload);
	}
}

@Configuration
@Import(value={FootballDomainContext.class,FootballIntegrationContext.class, RootApplicationProfilesContext.class})
class EventDrivenFootballGatewayIntegrationTestContext {
	
	@Bean
	public ClubService clubService() throws DuplicateClubNameException{
		ClubService clubService = Mockito.mock(ClubService.class);
		Mockito.when(clubService.createClub(Mockito.anyString())).thenReturn("Abc");
		return clubService;
	}
	
	@Bean
	@BridgeFrom(value="updateClubChannel")
	public QueueChannel testChannel(){
		return new QueueChannel(1);
	}
	
}
