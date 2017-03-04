package com.javangarda.fantacalcio.authserver.application.saga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.transaction.annotation.Transactional;

@MessageEndpoint
public class MessageHandler {

    @Autowired
    private TokenStore tokenStore;

    @Transactional
    public void foo(String email){
        tokenStore.removeAccessToken(tokenStore.readAccessToken("0c1814b7410673b1a290cca9a5b5a558"));
    }
}
