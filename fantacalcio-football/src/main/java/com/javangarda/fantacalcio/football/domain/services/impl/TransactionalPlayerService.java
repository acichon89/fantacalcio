package com.javangarda.fantacalcio.football.domain.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javangarda.fantacalcio.football.domain.data.CreatingPlayerDTO;
import com.javangarda.fantacalcio.football.domain.data.PlayerTransferDTO;
import com.javangarda.fantacalcio.football.domain.data.UpdatingPlayerDTO;
import com.javangarda.fantacalcio.football.domain.factories.PlayerFactory;
import com.javangarda.fantacalcio.football.domain.model.Club;
import com.javangarda.fantacalcio.football.domain.model.Player;
import com.javangarda.fantacalcio.football.domain.repositories.ClubRepository;
import com.javangarda.fantacalcio.football.domain.repositories.PlayerRepository;
import com.javangarda.fantacalcio.football.domain.services.PlayerService;
import com.javangarda.fantacalcio.util.convert.Converter;

@Service
public class TransactionalPlayerService implements PlayerService {

	@Autowired
	private PlayerFactory playerFactory;
	@Autowired
	private Converter<CreatingPlayerDTO, Player> creatingConverter;
	@Autowired
	private Converter<UpdatingPlayerDTO, Player> updateingConverter;
	@Autowired
	private ClubRepository clubRepository;
	@Autowired
	private PlayerRepository playerRepository;
	
	@Override
	public void createPlayer(CreatingPlayerDTO playerDTO) {
		Player newPlayer = playerFactory.create();
		Player convertedPlayer = creatingConverter.convertTo(playerDTO);
		newPlayer.merge(convertedPlayer);
		Club club = clubRepository.findOne(playerDTO.getClubId());
		newPlayer.setClub(club);
		playerRepository.save(newPlayer);
	}

	@Override
	public void updatePlayer(UpdatingPlayerDTO playerDTO) {
		Player player = playerRepository.findOne(playerDTO.getPlayerId());
		Player convertedPlayer = updateingConverter.convertTo(playerDTO);
		player.merge(convertedPlayer);
	}

	@Override
	public void transferPlayer(PlayerTransferDTO playerTransferDTO) {
		Player player = playerRepository.findOne(playerTransferDTO.getPlayerId());
		Club newClub = StringUtils.isBlank(playerTransferDTO.getClubId()) ? null : clubRepository.findOne(playerTransferDTO.getClubId());
		player.setClub(newClub);
	}

}
