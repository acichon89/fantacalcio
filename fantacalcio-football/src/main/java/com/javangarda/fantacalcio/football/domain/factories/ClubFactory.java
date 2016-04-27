package com.javangarda.fantacalcio.football.domain.factories;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.football.domain.model.Club;
import com.javangarda.fantacalcio.util.factories.EntityFactory;

@Component
public class ClubFactory implements EntityFactory<Club>{

	@Override
	public Club create() {
		Club club = new Club();
		club.setId(UUID.randomUUID().toString());
		return club;
	}

}
