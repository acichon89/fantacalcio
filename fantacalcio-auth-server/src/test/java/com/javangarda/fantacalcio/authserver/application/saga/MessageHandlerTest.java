package com.javangarda.fantacalcio.authserver.application.saga;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.oauth2.provider.token.TokenStore;

@RunWith(MockitoJUnitRunner.class)
public class MessageHandlerTest {

    @Mock
    private TokenStore tokenStore;

    @InjectMocks
    private MessageHandler messageHandler;

    @Test
    public void shouldQueryInTokenStore(){
        messageHandler.foo("abc@abc.com");
        Mockito.verify(tokenStore, Mockito.times(1)).readAccessToken("0c1814b7410673b1a290cca9a5b5a558");
    }
}