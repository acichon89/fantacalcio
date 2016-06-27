package com.javangarda.fantacalcio.user.port.adapter.socialapi;

import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.google.api.Google;

import com.javangarda.fantacalcio.user.domain.service.SocialConnectionResolver;
import com.javangarda.fantacalcio.user.domain.value.EstablishedUserSocialConnection;
import com.javangarda.fantacalcio.user.domain.value.SocialMediaType;

public class OauthApiSocialConnectionResolver implements SocialConnectionResolver {

	@Override
	public EstablishedUserSocialConnection create(Connection<?> connection) {
		EstablishedUserSocialConnection user = new EstablishedUserSocialConnection();
		Object api = connection.getApi();
		if(api instanceof Facebook) {
			Facebook fapi = (Facebook) api;
			user.setEmail(fapi.userOperations().getUserProfile().getEmail());
			user.setSocialMediaType(SocialMediaType.FACEBOOK);
			user.setFullName(fapi.userOperations().getUserProfile().getFirstName()+" "+fapi.userOperations().getUserProfile().getLastName());
		} else if(api instanceof Google) {
			Google tapi = (Google) api;
			user.setEmail(connection.fetchUserProfile().getEmail());
			user.setSocialMediaType(SocialMediaType.GOOGLE_PLUS);
			user.setFullName(connection.getDisplayName());
		}
		return user;
	}

	
}
