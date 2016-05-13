package com.javangarda.fantacalcio.football.domain.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.javangarda.fantacalcio.football.domain.data.CreatingPlayerDTO;
import com.javangarda.fantacalcio.football.domain.data.PlayerTransferDTO;
import com.javangarda.fantacalcio.football.domain.data.UpdatingPlayerDTO;
import com.javangarda.fantacalcio.football.domain.factories.PlayerFactory;
import com.javangarda.fantacalcio.football.domain.model.Club;
import com.javangarda.fantacalcio.football.domain.model.Player;
import com.javangarda.fantacalcio.football.domain.repositories.ClubRepository;
import com.javangarda.fantacalcio.football.domain.repositories.PlayerRepository;
import com.javangarda.fantacalcio.football.domain.values.PlayerPosition;
import com.javangarda.fantacalcio.util.convert.Converter;

@RunWith(MockitoJUnitRunner.class)
public class TransactionalPlayerServiceTest {

	@Mock
	private PlayerFactory playerFactory;
	@Mock
	private Converter<CreatingPlayerDTO, Player> creatingConverter;
	@Mock
	private Converter<UpdatingPlayerDTO, Player> updateingConverter;
	@Mock
	private ClubRepository clubRepository;
	@Mock
	private PlayerRepository playerRepository;
	
	@InjectMocks
	private TransactionalPlayerService service;
	
	@Test
	public void shouldStoreCreatedPlayer() {
		//given:
		CreatingPlayerDTO creatingPlayerDTO = CreatingPlayerDTO.builder().active(true).fullName("Claudio Marchisio")
				.position(PlayerPosition.MIDFIELDER).clubId("abc-xyz").build();
		Club juventus = Mockito.mock(Club.class);
		Mockito.when(clubRepository.getOne("abc-xyz")).thenReturn(juventus);
		Player convertedPlayer = new Player();
		convertedPlayer.setFullName("Claudio Marchisio");
		convertedPlayer.setPosition(PlayerPosition.MIDFIELDER);
		Mockito.when(creatingConverter.convertTo(creatingPlayerDTO)).thenReturn(convertedPlayer);
		Player createdPlayer = new Player();
		Mockito.when(playerFactory.create()).thenReturn(createdPlayer);
		//when:
		service.createPlayer(creatingPlayerDTO);
		//then:
		Mockito.verify(playerFactory).create();
		Mockito.verify(clubRepository).getOne("abc-xyz");
		Assert.assertEquals(juventus, createdPlayer.getClub());
		Assert.assertEquals("Claudio Marchisio", createdPlayer.getFullName());
	}
	
	@Test
	public void shouldUpdatePlayerWithGivenData() {
		//given:
		UpdatingPlayerDTO updatingPlayerDTO = UpdatingPlayerDTO.builder().fullName("Stephen El Shaarawy")
				.position(PlayerPosition.FORWARD).playerId("bbb-ttt").build();
		Player persistedPlayer = new Player();
		persistedPlayer.setPosition(PlayerPosition.MIDFIELDER);
		persistedPlayer.setFullName("Stefan ElSzaarawy");
		Player convertedPlayer = new Player();
		convertedPlayer.setPosition(PlayerPosition.FORWARD);
		convertedPlayer.setFullName("Stephen El Shaarawy");
		Mockito.when(updateingConverter.convertTo(updatingPlayerDTO)).thenReturn(convertedPlayer);
		Mockito.when(playerRepository.findOne(updatingPlayerDTO.getPlayerId())).thenReturn(persistedPlayer);
		//when:
		service.updatePlayer(updatingPlayerDTO);
		//then:
		Assert.assertEquals(PlayerPosition.FORWARD, persistedPlayer.getPosition());
		Assert.assertEquals("Stephen El Shaarawy", persistedPlayer.getFullName());
	}

	@Test
	public void shouldChangeTeamWhileTransfering() {
		//given:
		PlayerTransferDTO playerTransferDTO = PlayerTransferDTO.builder().clubId("jjj-vvv").playerId("mm-hh").build();
		Player marekHamsik = new Player();
		Club napoli = new Club();
		marekHamsik.setClub(napoli);
		Club juventus = new Club();
		Mockito.when(playerRepository.findOne("mm-hh")).thenReturn(marekHamsik);
		Mockito.when(clubRepository.getOne("jjj-vvv")).thenReturn(juventus);
		//when:
		service.transferPlayer(playerTransferDTO);
		//then:
		Assert.assertEquals(juventus, marekHamsik.getClub());
	}
	
	@Test
	public void shouldSetNoClubWhileTransfering() {
		//given:
		PlayerTransferDTO playerTransferDTO = PlayerTransferDTO.builder().clubId(null).playerId("mm-hh").build();
		Player marekHamsik = new Player();
		Club napoli = new Club();
		marekHamsik.setClub(napoli);
		Mockito.when(playerRepository.findOne("mm-hh")).thenReturn(marekHamsik);
		//when:
		service.transferPlayer(playerTransferDTO);
		//then:
		Assert.assertEquals(null, marekHamsik.getClub());
	}
}
