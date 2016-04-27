package com.javangarda.fantacalcio.football.domain.converters;

import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.football.domain.data.ClubDTO;
import com.javangarda.fantacalcio.football.domain.model.Club;
import com.javangarda.fantacalcio.util.convert.AbstractDozerConverter;

@Component
public class ClubEntityDTOConverter extends AbstractDozerConverter<ClubDTO, Club> {

	public ClubEntityDTOConverter() {
		super(ClubDTO.class, Club.class);
	}

}
