package com.javangarda.fantacalcio.football.domain.gateways.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.javangarda.fantacalcio.football.domain.data.ClubDTO;
import com.javangarda.fantacalcio.football.domain.data.CreatingPlayerDTO;
import com.javangarda.fantacalcio.football.domain.data.PlayerTransferDTO;
import com.javangarda.fantacalcio.football.domain.data.UpdatingPlayerDTO;
import com.javangarda.fantacalcio.football.domain.events.ClubUpdatedEventPublisher;
import com.javangarda.fantacalcio.football.domain.events.DuplicateClubNameException;
import com.javangarda.fantacalcio.football.domain.events.PlayerTransferedEventPublisher;
import com.javangarda.fantacalcio.football.domain.events.PlayerUpdatedEventPublisher;
import com.javangarda.fantacalcio.football.domain.services.ClubService;
import com.javangarda.fantacalcio.football.domain.services.PlayerService;

@RunWith(MockitoJUnitRunner.class)
public class EventDrivenFootballGatewayTest {

	@Mock
	private ClubService clubService;
	@Mock
	private PlayerService playerService;
	@Mock
	private ClubUpdatedEventPublisher clubUpdatedEventPublisher;
	@Mock
	private PlayerTransferedEventPublisher playerTransferedEventPublisher;
	@Mock
	private PlayerUpdatedEventPublisher playerUpdatedEventPublisher;
	
	@InjectMocks
	private EventDrivenFootballGateway gateway;
	
	@Test
	public void shouldDelegateToServiceWhileCreatingClub() throws DuplicateClubNameException {
		//given:
		String clubName = "Juventus";
		//when:
		gateway.createClub(clubName);
		//then:
		Mockito.verify(clubService).createClub(clubName);
	}
	
	@Test(expected=DuplicateClubNameException.class)
	public void shouldThrowErrorWhileCreatingClubWithDuplicatedName() throws DuplicateClubNameException {
		//given:
		String clubName = "AC Milan";
		Mockito.doThrow(DuplicateClubNameException.class).when(clubService).createClub(clubName);
		//when:
		gateway.createClub(clubName);
		//then:
		// .... expect throwed Exception!
	}
	
	@Test
	public void shouldDelegateToServiceWhileUpdatingClub() throws DuplicateClubNameException {
		//given:
		ClubDTO clubDTO = Mockito.any(ClubDTO.class);
		//when:
		gateway.updateClub(clubDTO);
		//then:
		Mockito.verify(clubService).updateClub(clubDTO);
	}

	@Test(expected=DuplicateClubNameException.class)
	public void shouldThrowErrorAndNotSendEventWhileUpdatingClubWithDuplicatedName() throws DuplicateClubNameException {
		//given:
		ClubDTO clubDTO = ClubDTO.builder().active(true).name("AS Roma").id("123").build();
		Mockito.doThrow(DuplicateClubNameException.class).when(clubService).updateClub(Mockito.eq(clubDTO));
		//when:
		gateway.updateClub(clubDTO);
		//then:
		// .... expect throwed Exception!
		Mockito.verify(clubUpdatedEventPublisher, Mockito.never()).publish(clubDTO);
	}
	
	@Test
	public void shouldDelegateToServiceWhileCreatingPlayer() {
		//given:
		CreatingPlayerDTO creatingPlayerDTO = Mockito.any(CreatingPlayerDTO.class);
		//when:
		gateway.createPlayer(creatingPlayerDTO);
		//then:
		Mockito.verify(playerService).createPlayer(creatingPlayerDTO);
	}
	
	@Test
	public void shouldDelegateToServiceAndSendEventWhileUpdatingPlayer() {
		//given:
		UpdatingPlayerDTO updatingPlayerDTO = Mockito.any(UpdatingPlayerDTO.class);
		//when:
		gateway.updatePlayer(updatingPlayerDTO);
		//then:
		Mockito.verify(playerService).updatePlayer(updatingPlayerDTO);
		Mockito.verify(playerUpdatedEventPublisher).publish(updatingPlayerDTO);
	}
	
	@Test
	public void shouldDelegateToServiceAndSendEventWhileTransferingPlayer() {
		//given:
		PlayerTransferDTO playerTransferDTO = Mockito.any(PlayerTransferDTO.class);
		//when:
		gateway.transferPlayer(playerTransferDTO);
		//then:
		Mockito.verify(playerService).transferPlayer(playerTransferDTO);
		Mockito.verify(playerTransferedEventPublisher).publish(playerTransferDTO);
	}
}
