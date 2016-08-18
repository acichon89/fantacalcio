package com.javangarda.fantacalcio.user.application.internal.impl;

import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.SignUpSocialConnection;
import com.javangarda.fantacalcio.user.application.internal.SocialConnectionResolver;

@Component
public class OauthApiSocialConnectionResolver implements SocialConnectionResolver {

	@Override
	public SignUpSocialConnection create(Connection<?> connection) {
		Object api = connection.getApi();
		if(api instanceof Facebook) {
			Facebook fapi = (Facebook) api;
			String email = fapi.userOperations().getUserProfile().getEmail();
			String fullName = fapi.userOperations().getUserProfile().getFirstName()+" "+fapi.userOperations().getUserProfile().getLastName();
			return new SignUpSocialConnection(email, fullName);
		}
		return null;
	}

	
}
