package com.javangarda.fantacalcio.user.infrastructure.port.adapter.messaging;

import com.javangarda.fantacalcio.user.application.internal.ExternalMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class KafkaExternalMessageSender implements ExternalMessageSender {

    @Autowired
    private Source source;

    @Override
    public void removeUserTokens(String email) {
        Message<String> msg = MessageBuilder.withPayload(email).build();
        this.source.output().send(msg);
    }
}
