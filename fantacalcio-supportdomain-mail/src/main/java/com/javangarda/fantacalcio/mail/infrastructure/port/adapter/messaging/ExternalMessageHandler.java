package com.javangarda.fantacalcio.mail.infrastructure.port.adapter.messaging;

import com.javangarda.fantacalcio.mail.application.gateway.EmailGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ExternalMessageHandler {

    @Autowired
    private EmailGateway emailGateway;

    @ServiceActivator(inputChannel = Events.SUBSCRIBE_MAIL_INPUT)
    public void handle(@Payload String email, @Header("activationHash") String activationHash){
        emailGateway.sendActivationEmail(email, activationHash);
    }
}
