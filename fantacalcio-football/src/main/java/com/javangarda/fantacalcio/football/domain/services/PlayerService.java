package com.javangarda.fantacalcio.football.domain.services;

import com.javangarda.fantacalcio.football.domain.data.CreatingPlayerDTO;
import com.javangarda.fantacalcio.football.domain.data.PlayerTransferDTO;
import com.javangarda.fantacalcio.football.domain.data.UpdatingPlayerDTO;

public interface PlayerService {

	String createPlayer(CreatingPlayerDTO playerDTO);
	void updatePlayer(UpdatingPlayerDTO playerDTO);
	void transferPlayer(PlayerTransferDTO playerTransferDTO);
}
