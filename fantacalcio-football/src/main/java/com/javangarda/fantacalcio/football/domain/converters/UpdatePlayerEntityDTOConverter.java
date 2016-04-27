package com.javangarda.fantacalcio.football.domain.converters;

import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.football.domain.data.UpdatingPlayerDTO;
import com.javangarda.fantacalcio.football.domain.model.Player;
import com.javangarda.fantacalcio.util.convert.AbstractDozerConverter;

@Component
public class UpdatePlayerEntityDTOConverter extends AbstractDozerConverter<UpdatingPlayerDTO, Player> {

	public UpdatePlayerEntityDTOConverter() {
		super(UpdatingPlayerDTO.class, Player.class);
	}

}
