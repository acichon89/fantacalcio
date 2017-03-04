package com.javangarda.fantacalcio.commons.authentication;


import java.util.Optional;

public interface CurrentUserResolver {
    Optional<String> resolveCurrentUserIdentity();
}
