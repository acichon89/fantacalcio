package com.javangarda.fantacalcio.football.domain.services;

import com.javangarda.fantacalcio.football.domain.data.ClubDTO;
import com.javangarda.fantacalcio.football.domain.events.DuplicateClubNameException;

public interface ClubService {

	String createClub(String name) throws DuplicateClubNameException;
	void updateClub(ClubDTO dto) throws DuplicateClubNameException;
}
