package com.javangarda.fantacalcio.authserver.application.gateway.impl;

import com.javangarda.fantacalcio.authserver.application.internal.TokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EventDrivenAuthGatewayTest {

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private EventDrivenAuthGateway eventDrivenAuthGateway;

    @Test
    public void shouldDelegateToService(){
        //given:
        String email = "john@smith.com";
        //when:
        eventDrivenAuthGateway.removeToken(email);
        //then:
        Mockito.verify(tokenService).removeTokenByMail(email);
    }
}