package com.javangarda.fantacalcio.user.domain.service;

import org.springframework.social.connect.Connection;

import com.javangarda.fantacalcio.user.domain.value.EstablishedUserSocialConnection;

public interface SocialConnectionResolver {

	EstablishedUserSocialConnection create(Connection<?> connection);
}
