package com.javangarda.fantacalcio.authserver.application.gateway.impl;

import com.javangarda.fantacalcio.authserver.application.gateway.AuthGateway;
import com.javangarda.fantacalcio.authserver.application.internal.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventDrivenAuthGateway implements AuthGateway {

    @Autowired
    private TokenService tokenService;

    @Override
    public void removeToken(String email) {
        tokenService.removeTokenByMail(email);
    }
}
