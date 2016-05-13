package com.javangarda.fantacalcio.football.domain.data;

import org.joda.time.DateTime;

import com.javangarda.fantacalcio.football.domain.values.PlayerPosition;

import lombok.Data;
import lombok.experimental.Builder;

@Data
@Builder
public class CreatingPlayerDTO {

	private String fullName;
	private boolean active;
	private PlayerPosition position;
	private DateTime dateOfBirth;
	private String clubId;
}
