package com.javangarda.fantacalcio.user.application.event;

import org.springframework.integration.annotation.MessagingGateway;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDto;

@MessagingGateway
public interface UserEventPublisher {

	void publishUserCreated(RegistrationUserDto registerDto);
	
}
