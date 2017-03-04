package com.javangarda.fantacalcio.commons.authentication;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


public class SecurityContextCurrentUserResolver implements CurrentUserResolver {

    public Optional<String> resolveCurrentUserIdentity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return Optional.of(authentication.getName());
        } else {
            return Optional.empty();
        }
    }
}
