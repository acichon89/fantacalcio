package com.javangarda.fantacalcio.football.domain.events;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import com.javangarda.fantacalcio.football.domain.data.ClubDTO;

@MessagingGateway
public interface ClubUpdatedEventPublisher {

	@Gateway(requestChannel="updateClubChannel")
	void publish(ClubDTO clubDTO);
}
