package com.javangarda.fantacalcio.user.infrastructure.port.adapter.messaging;

import com.javangarda.fantacalcio.user.application.event.EmailCommandSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ExternalMessageEmailCommandSender implements EmailCommandSender {

    @Autowired
    private Events events;

    @Override
    public void sendConfirmationEmail(@Payload String email, @Header("activationToken") String token) {
        Message<String> message = MessageBuilder.withPayload(email).setHeader("activationToken", token).build();
        events.activationMailChannel().send(message);
    }
}
