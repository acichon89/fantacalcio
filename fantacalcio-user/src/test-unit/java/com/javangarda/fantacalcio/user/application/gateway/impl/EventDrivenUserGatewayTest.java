package com.javangarda.fantacalcio.user.application.gateway.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.connect.Connection;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDto;
import com.javangarda.fantacalcio.user.application.event.DuplicateEmailException;
import com.javangarda.fantacalcio.user.application.event.EmailCommandSender;
import com.javangarda.fantacalcio.user.application.event.EmailNotFoundException;
import com.javangarda.fantacalcio.user.application.event.UserEventPublisher;
import com.javangarda.fantacalcio.user.application.internal.UserConnectionService;
import com.javangarda.fantacalcio.user.application.internal.UserService;

@RunWith(MockitoJUnitRunner.class)
public class EventDrivenUserGatewayTest {

	@Mock
	private UserService userService;
	@Mock
	private UserConnectionService userConnectionService;
	@Mock
	private UserEventPublisher userEventPublisher;
	@Mock
	private EmailCommandSender emailCommandSender;
	
	@InjectMocks
	private EventDrivenUserGateway eventDrivenUserGateway = Mockito.spy(new EventDrivenUserGateway());
	
	@Test
	public void foo() throws DuplicateEmailException {
		//given:
		RegistrationUserDto registerDto = new RegistrationUserDto();
		registerDto.setFullName("John Doe");
		registerDto.setPassword("pass");
		//when:
		eventDrivenUserGateway.registerUser(registerDto);
		//then:
		Mockito.verify(userService).registerUser(registerDto);
		Mockito.verify(userEventPublisher).publishUserCreated(registerDto);
	}
	
	@Test
	public void shouldSendCommandAfterAssignActivationToken() throws EmailNotFoundException {
		//given:
		String email = "abc@abc.pl";
		Mockito.when(userService.assignActivationToken(email)).thenReturn("tokenTokenToken");
		//when:
		eventDrivenUserGateway.startConfirmationEmailProcedure(email);
		//then:
		Mockito.verify(emailCommandSender).sendConfirmationEmail(email, "tokenTokenToken");
	}
	
	@Test
	public void shouldDelegateWhileSavingConnection() {
		//given:
		Connection connection = Mockito.mock(Connection.class);
		String email = "abc@abc.pl";
		//when:
		eventDrivenUserGateway.saveConnection(connection, email);
		//then:
		Mockito.verify(userConnectionService).saveUserConnection(email, connection);
	}

}
