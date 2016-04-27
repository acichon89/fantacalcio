package com.javangarda.fantacalcio.football.domain.converters;

import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.football.domain.data.CreatingPlayerDTO;
import com.javangarda.fantacalcio.football.domain.model.Player;
import com.javangarda.fantacalcio.util.convert.AbstractDozerConverter;

@Component
public class CreatePlayerEntityDTOConverter extends AbstractDozerConverter<CreatingPlayerDTO, Player> {

	public CreatePlayerEntityDTOConverter() {
		super(CreatingPlayerDTO.class, Player.class);
	}

}
