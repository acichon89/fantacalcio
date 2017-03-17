package com.javangarda.fantacalcio.user.infrastructure.port.adapter.messaging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ExternalMessageEmailCommandSenderTest {

    @Mock
    private Events events;

    @InjectMocks
    private ExternalMessageEmailCommandSender externalMessageEmailCommandSender;

    @Test
    public void shouldSendMessageToChannel(){
        //given:
        MessageChannel messageChannel = Mockito.mock(MessageChannel.class);
        Mockito.when(events.activationMailChannel()).thenReturn(messageChannel);
        //when:
        externalMessageEmailCommandSender.sendConfirmationEmail("email@example.com", "test123");
        //then:
        ArgumentCaptor<Message> messageArgumentCaptor = ArgumentCaptor.forClass(Message.class);
        Mockito.verify(messageChannel).send(messageArgumentCaptor.capture());
        assertEquals("email@example.com", messageArgumentCaptor.getValue().getPayload());
        assertEquals("test123", messageArgumentCaptor.getValue().getHeaders().get("activationToken"));
    }
}