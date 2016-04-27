package com.javangarda.fantacalcio.football.domain.data;

import lombok.Data;
import lombok.experimental.Builder;

@Data
@Builder
public class ClubDTO {

	private String id;
	private String name;
	private boolean active;
}
