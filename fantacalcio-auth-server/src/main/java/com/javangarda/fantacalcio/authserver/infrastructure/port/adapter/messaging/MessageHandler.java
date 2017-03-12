package com.javangarda.fantacalcio.authserver.infrastructure.port.adapter.messaging;

import com.javangarda.fantacalcio.authserver.application.gateway.AuthGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {

    @Autowired
    private AuthGateway authGateway;

    @ServiceActivator(inputChannel = Events.REMOVE_OAUTH_TOKEN_INPUT)
    public void handleRemoveOauthTokenEvent(String email){
        authGateway.removeToken(email);
    }
}
