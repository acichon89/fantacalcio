package com.javangarda.fantacalcio.user.application.event;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingGateway
public interface UserEventPublisher {

	@Gateway(requestChannel="userRegisteredChannel")
	void publishUserCreated(@Payload RegistrationUserDTO registerDto, @Header("userId") String id);

	@Gateway(requestChannel = "emailConfirmedChannel")
	void publishEmailConfirmed(String email);

	@Gateway(requestChannel = "userBannedChannel")
	void publishUserBanned(String email);

	@Gateway(requestChannel = "passwordResetChannel")
	void publishResetPasswordConfirmed(@Payload String token,@Header("email") String email);
}
