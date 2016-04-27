package com.javangarda.fantacalcio.football.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
@IntegrationComponentScan("com.javangarda.fantacalcio.football.domain.events")
public class FootballIntegrationContext {

	@Bean
	public MessageChannel updateClubChannel() {
		return new PublishSubscribeChannel();
	}
	
	@Bean
	public MessageChannel updatePlayerChannel() {
		return new PublishSubscribeChannel();
	}
	
	@Bean
	public MessageChannel playerTransferChannel() {
		return new PublishSubscribeChannel();
	}
}
