package com.javangarda.fantacalcio.authserver.infrastructure.port.adapter.messaging;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Events {
    String REMOVE_OAUTH_TOKEN_INPUT = "removeOauthTokenSubscribeChannel";

    @Input(REMOVE_OAUTH_TOKEN_INPUT)
    SubscribableChannel input();
}
