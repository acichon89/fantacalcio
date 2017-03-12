package com.javangarda.fantacalcio.user.infrastructure.port.adapter.messaging;

import com.javangarda.fantacalcio.user.application.internal.AuthCommandSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class ExternalMessageAuthCommandSender implements AuthCommandSender {

    @Autowired
    private Events events;

    @Override
    public void removeAccessToken(String email){
        events.removeTokenChannel().send(new GenericMessage<>(email));
    }
}
