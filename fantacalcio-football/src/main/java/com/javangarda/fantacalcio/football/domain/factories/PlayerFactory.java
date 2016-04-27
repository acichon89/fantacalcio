package com.javangarda.fantacalcio.football.domain.factories;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.football.domain.model.Player;
import com.javangarda.fantacalcio.util.factories.EntityFactory;

@Component
public class PlayerFactory implements EntityFactory<Player>{

	@Override
	public Player create() {
		Player player = new Player();
		player.setId(UUID.randomUUID().toString());
		return player;
	}

}
