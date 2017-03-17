package com.javangarda.fantacalcio.user.infrastructure.port.adapter.messaging;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.validateMockitoUsage;

@RunWith(MockitoJUnitRunner.class)
public class ExternalMessageAuthCommandSenderTest {

    @Mock
    private Events events;

    @InjectMocks
    private ExternalMessageAuthCommandSender externalMessageAuthCommandSender;

    @Test
    public void shouldSendMessageToChannel(){
        //given:
        MessageChannel messageChannel = Mockito.mock(MessageChannel.class);
        Mockito.when(events.removeTokenChannel()).thenReturn(messageChannel);
        //when:
        externalMessageAuthCommandSender.removeAccessToken("homer@greece.com");
        //then:
        ArgumentCaptor<GenericMessage> genericMessageArgumentCaptor = ArgumentCaptor.forClass(GenericMessage.class);
        Mockito.verify(messageChannel).send(genericMessageArgumentCaptor.capture());
        assertEquals("homer@greece.com", genericMessageArgumentCaptor.getValue().getPayload());
    }

    @After
    public void validate() {
        validateMockitoUsage();
    }
}