package com.javangarda.fantacalcio.user.infrastructure.port.adapter.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Events {

    @Output
    MessageChannel removeTokenChannel();

    @Output
    MessageChannel activationMailChannel();
}
