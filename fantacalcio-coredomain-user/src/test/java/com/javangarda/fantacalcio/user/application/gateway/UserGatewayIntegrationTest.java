package com.javangarda.fantacalcio.user.application.gateway;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.internal.UserService;
import com.javangarda.fantacalcio.user.infrastructure.port.adapter.messaging.Events;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserGatewayIntegrationTest {

    @Autowired
    private UserGateway userGateway;

    @MockBean
    private UserService userService;
    @MockBean
    private Events events;

    @Test
    public void registrationTest() throws InterruptedException {
        //given:
        RegistrationUserDTO dto = new RegistrationUserDTO();
        dto.setEmail("john@doe.com");
        dto.setFullName("John Doe");
        dto.setPassword("pass");
        dto.setConfirmedPassword("pass");

        Mockito.when(userService.registerUser(Mockito.eq(dto))).thenReturn("123");
        Mockito.when(userService.assignActivationToken("john@doe.com", "123")).thenReturn(Optional.of("token123"));

        MessageChannel messageChannel = Mockito.mock(MessageChannel.class);
        Mockito.when(events.activationMailChannel()).thenReturn(messageChannel);
        //when:
        userGateway.registerUser(dto);
        Thread.sleep(1000);
        //then:
        Mockito.verify(userService).registerUser(dto);
        Mockito.verify(userService).assignActivationToken("john@doe.com", "123");

        ArgumentCaptor<Message> messageAC = ArgumentCaptor.forClass(Message.class);
        Mockito.verify(messageChannel).send(messageAC.capture());

        Message message = messageAC.getValue();
        assertEquals("john@doe.com", message.getPayload());
        assertEquals("token123", message.getHeaders().get("activationToken"));
    }
}