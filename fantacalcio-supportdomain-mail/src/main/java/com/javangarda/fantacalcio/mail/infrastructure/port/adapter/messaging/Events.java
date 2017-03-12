package com.javangarda.fantacalcio.mail.infrastructure.port.adapter.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Events {

    String SUBSCRIBE_MAIL_INPUT = "confirmationMailSubscribeChannel";

    @Input(SUBSCRIBE_MAIL_INPUT)
    SubscribableChannel input();
}
