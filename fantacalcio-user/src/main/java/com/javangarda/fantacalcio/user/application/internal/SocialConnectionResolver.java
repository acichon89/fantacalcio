package com.javangarda.fantacalcio.user.application.internal;

import org.springframework.social.connect.Connection;

import com.javangarda.fantacalcio.user.application.data.SignUpSocialConnection;

public interface SocialConnectionResolver {

	SignUpSocialConnection create(Connection<?> connection);
}
