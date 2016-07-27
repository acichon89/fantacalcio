package com.javangarda.fantacalcio.user.application.internal;

import org.springframework.social.connect.Connection;

import com.javangarda.fantacalcio.user.application.data.EstablishedUserSocialConnection;

public interface SocialConnectionResolver {

	EstablishedUserSocialConnection create(Connection<?> connection);
}