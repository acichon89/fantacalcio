package com.javangarda.fantacalcio.football.domain.events;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import com.javangarda.fantacalcio.football.domain.data.UpdatingPlayerDTO;

@MessagingGateway
public interface PlayerUpdatedEventPublisher {

	@Gateway(requestChannel="updatePlayerChannel")
	void publish(UpdatingPlayerDTO updatingPlayerDTO);
}
