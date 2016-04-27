package com.javangarda.fantacalcio.football.domain.gateways.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javangarda.fantacalcio.football.domain.data.ClubDTO;
import com.javangarda.fantacalcio.football.domain.data.CreatingPlayerDTO;
import com.javangarda.fantacalcio.football.domain.data.PlayerTransferDTO;
import com.javangarda.fantacalcio.football.domain.data.UpdatingPlayerDTO;
import com.javangarda.fantacalcio.football.domain.events.ClubUpdatedEventPublisher;
import com.javangarda.fantacalcio.football.domain.events.DuplicateClubNameException;
import com.javangarda.fantacalcio.football.domain.events.PlayerTransferedEventPublisher;
import com.javangarda.fantacalcio.football.domain.events.PlayerUpdatedEventPublisher;
import com.javangarda.fantacalcio.football.domain.gateways.FootballGateway;
import com.javangarda.fantacalcio.football.domain.services.ClubService;
import com.javangarda.fantacalcio.football.domain.services.PlayerService;

@Service
public class EventDrivenFootballGateway implements FootballGateway {

	@Autowired
	private ClubService clubService;
	@Autowired
	private PlayerService playerService;
	@Autowired
	private ClubUpdatedEventPublisher clubUpdatedEventPublisher;
	@Autowired
	private PlayerTransferedEventPublisher playerTransferedEventPublisher;
	@Autowired
	private PlayerUpdatedEventPublisher playerUpdatedEventPublisher;

	@Override
	public String createClub(String name) throws DuplicateClubNameException {
		return clubService.createClub(name);
	}

	@Override
	public void updateClub(ClubDTO dto) throws DuplicateClubNameException {
		clubService.updateClub(dto);
		clubUpdatedEventPublisher.publish(dto);
	}

	@Override
	public void createPlayer(CreatingPlayerDTO playerDTO) {
		playerService.createPlayer(playerDTO);
	}

	@Override
	public void updatePlayer(UpdatingPlayerDTO playerDTO) {
		playerService.updatePlayer(playerDTO);
		playerUpdatedEventPublisher.publish(playerDTO);
	}

	@Override
	public void transferPlayer(PlayerTransferDTO playerTransferDTO) {
		playerService.transferPlayer(playerTransferDTO);
		playerTransferedEventPublisher.publish(playerTransferDTO);
	}	
}
