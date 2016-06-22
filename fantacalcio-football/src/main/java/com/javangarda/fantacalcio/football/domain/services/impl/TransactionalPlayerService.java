package com.javangarda.fantacalcio.football.domain.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
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
	public String createPlayer(CreatingPlayerDTO playerDTO) {
		Player newPlayer = playerFactory.create();
		Player convertedPlayer = creatingConverter.convertTo(playerDTO);
		newPlayer.merge(convertedPlayer, true);
		Club club = clubRepository.getOne(playerDTO.getClubId());
		newPlayer.setClub(club);
		playerRepository.save(newPlayer);
		return newPlayer.getId();
	}

	@Override
	public void updatePlayer(UpdatingPlayerDTO playerDTO) {
		Player player = playerRepository.findOne(playerDTO.getPlayerId());
		Player convertedPlayer = updateingConverter.convertTo(playerDTO);
		player.merge(convertedPlayer, false);
	}

	@Override
	public void transferPlayer(PlayerTransferDTO playerTransferDTO) {
		Player player = playerRepository.findOne(playerTransferDTO.getPlayerId());
		Club newClub = StringUtils.isBlank(playerTransferDTO.getClubId()) ? null : clubRepository.getOne(playerTransferDTO.getClubId());
		player.setClub(newClub);
	}

}
