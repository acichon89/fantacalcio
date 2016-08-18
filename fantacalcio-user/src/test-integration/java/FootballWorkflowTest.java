import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.BridgeFrom;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;
import org.springframework.social.connect.Connection;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDto;
import com.javangarda.fantacalcio.user.application.event.DuplicateEmailException;
import com.javangarda.fantacalcio.user.application.event.EmailNotFoundException;
import com.javangarda.fantacalcio.user.application.gateway.impl.EventDrivenUserGateway;
import com.javangarda.fantacalcio.user.application.internal.UserConnectionService;
import com.javangarda.fantacalcio.user.application.internal.UserService;
import com.javangarda.fantacalcio.user.context.application.UserIntegrationsContext;
import com.javangarda.fantacalcio.util.testsupport.DisableAutowireRequireInitializer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=EventDrivenUserGatewayIntegrationTestContext.class, initializers={DisableAutowireRequireInitializer.class})
public class FootballWorkflowTest {

	@Autowired
	private EventDrivenUserGateway gateway;
	@Autowired
	private UserService userService;
	@Autowired
	private UserConnectionService userConnectionService;
	@Autowired
	private QueueChannel sendingConfirmationEmailQueue;
	
	@After
	public void clean(){
		Mockito.reset(userService);
		Mockito.reset(userConnectionService);
		sendingConfirmationEmailQueue.clear();
	}
	
	@Test
	public void testWorkflowWithConnection() throws DuplicateEmailException, InterruptedException, EmailNotFoundException {
		//given:
		String token = "bhjgdfbgjdfb";
		String email = "john@doe.com";
		Mockito.when(userService.assignActivationToken(email)).thenReturn(token);
		RegistrationUserDto dto = create(email, true);
		//when:
		gateway.registerUser(dto);
		Thread.currentThread().sleep(800);
		//then:
		Mockito.verify(userService).registerUser(dto);
		Mockito.verify(userService).assignActivationToken(email);
		Mockito.verify(userConnectionService).saveUserConnection(Mockito.eq(email), Mockito.any(Connection.class));
		Message<?> message = sendingConfirmationEmailQueue.receive(0);
		String emailPayload = (String) message.getPayload();
		Assert.assertEquals(email, emailPayload);
		Assert.assertEquals(token, message.getHeaders().get("activationToken"));
	}
	
	@Test
	public void testWorkflowWithoutConnection() throws DuplicateEmailException, InterruptedException, EmailNotFoundException {
		//given:
		String token = "bhjgdfbgjdfb";
		String email = "john@doe.com";
		Mockito.when(userService.assignActivationToken(email)).thenReturn(token);
		RegistrationUserDto dto = create(email, false);
		//when:
		gateway.registerUser(dto);
		Thread.currentThread().sleep(800);
		//then:
		Mockito.verify(userService).registerUser(dto);
		Mockito.verify(userService).assignActivationToken(email);
		Mockito.verify(userConnectionService, Mockito.never()).saveUserConnection(Mockito.anyString(), Mockito.any(Connection.class));
		Message<?> message = sendingConfirmationEmailQueue.receive(0);
		String emailPayload = (String) message.getPayload();
		Assert.assertEquals(email, emailPayload);
		Assert.assertEquals(token, message.getHeaders().get("activationToken"));
	}
	
	private RegistrationUserDto create(String email, boolean withConnection){
		RegistrationUserDto dto = new RegistrationUserDto();
		dto.setConfirmedPassword("aaaa");
		dto.setEmail(email);
		dto.setFullName("Abdul Srabdul");
		dto.setPassword("aaaa");
		Optional<Connection<?>> opt = withConnection ? Optional.of(Mockito.mock(Connection.class)) : Optional.empty();
		dto.setConnection(opt);
		return dto;
	}

}
@Configuration
@Import(value={UserIntegrationsContext.class} )
class EventDrivenUserGatewayIntegrationTestContext {
	
	@Bean
	public UserService userService() {
		return Mockito.mock(UserService.class);
	}
	
	@Bean
	public UserConnectionService userConnectionService() {
		return Mockito.mock(UserConnectionService.class);
	}

	@Bean
	@BridgeFrom(value="sendingConfirmationEmailChannel")
	public QueueChannel sendingConfirmationEmailQueue(){
		return new QueueChannel(1);
	}
}
