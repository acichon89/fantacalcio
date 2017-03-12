package com.javangarda.fantacalcio.authserver.application.internal.impl;

import com.javangarda.fantacalcio.authserver.application.internal.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class TransactionalTokenService implements TokenService {

    private static final String BROWSER_CLIENT_ID = "browser";

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void removeTokenByMail(String mail) {
        tokenStore.findTokensByClientIdAndUserName(BROWSER_CLIENT_ID, mail).stream().forEach(token -> tokenStore.removeAccessToken(token));
    }
}
