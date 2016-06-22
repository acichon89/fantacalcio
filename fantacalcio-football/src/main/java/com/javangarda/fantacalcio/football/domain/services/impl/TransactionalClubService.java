package com.javangarda.fantacalcio.football.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javangarda.fantacalcio.football.domain.data.ClubDTO;
import com.javangarda.fantacalcio.football.domain.events.DuplicateClubNameException;
import com.javangarda.fantacalcio.football.domain.factories.ClubFactory;
import com.javangarda.fantacalcio.football.domain.model.Club;
import com.javangarda.fantacalcio.football.domain.repositories.ClubRepository;
import com.javangarda.fantacalcio.football.domain.services.ClubService;
import com.javangarda.fantacalcio.util.convert.Converter;

@Service
@Transactional
public class TransactionalClubService implements ClubService {

	@Autowired
	private ClubRepository clubRepository;
	@Autowired
	private ClubFactory clubFactory;
	@Autowired
	private Converter<ClubDTO, Club> clubConverter;
	
	@Override
	public String createClub(String name) throws DuplicateClubNameException {
		//asserts:
		int clubsWithTheSameName = clubRepository.countByName(name);
		if(clubsWithTheSameName > 0){
			throw new DuplicateClubNameException(name);
		}
		//business logic:
		Club club = clubFactory.create();
		club.setName(name);
		club.setActive(false);
		clubRepository.save(club);
		return club.getId();
	}

	@Override
	public void updateClub(ClubDTO dto) throws DuplicateClubNameException {
		//asserts:
		int clubsWithTheSameName = clubRepository.countByNameAndNotId(dto.getName(), dto.getId());
		if(clubsWithTheSameName > 0){
			throw new DuplicateClubNameException(dto.getName());
		}
		//business logic:
		Club club = clubRepository.findOne(dto.getId());
		Club newestClubChanges = clubConverter.convertTo(dto);
		club.merge(newestClubChanges, false);
	}

}
