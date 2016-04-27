package com.javangarda.fantacalcio.football.domain.gateways;

import com.javangarda.fantacalcio.football.domain.data.ClubDTO;
import com.javangarda.fantacalcio.football.domain.data.CreatingPlayerDTO;
import com.javangarda.fantacalcio.football.domain.data.PlayerTransferDTO;
import com.javangarda.fantacalcio.football.domain.data.UpdatingPlayerDTO;
import com.javangarda.fantacalcio.football.domain.events.DuplicateClubNameException;

public interface FootballGateway {

	String createClub(String name) throws DuplicateClubNameException;
	void updateClub(ClubDTO dto) throws DuplicateClubNameException;
	void createPlayer(CreatingPlayerDTO playerDTO);
	void updatePlayer(UpdatingPlayerDTO playerDTO);
	void transferPlayer(PlayerTransferDTO playerTransferDTO);
}
