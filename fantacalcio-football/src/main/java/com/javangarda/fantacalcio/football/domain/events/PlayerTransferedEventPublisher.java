package com.javangarda.fantacalcio.football.domain.events;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import com.javangarda.fantacalcio.football.domain.data.PlayerTransferDTO;

@MessagingGateway
public interface PlayerTransferedEventPublisher {

	@Gateway(requestChannel="playerTransferChannel")
	void publish(PlayerTransferDTO dto);
}
