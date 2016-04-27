package com.javangarda.fantacalcio.football.domain.data;

import lombok.Data;
import lombok.experimental.Builder;

@Data
@Builder
public class PlayerTransferDTO {

	private String playerId;
	private String clubId;
}
