package com.javangarda.fantacalcio.user.application.internal.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.internal.LoggedUserResolver;

@Component
public class SocialContextLoggedUserResolver implements LoggedUserResolver {

	@Override
	public String getLoggedOnUserId() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return username.equals("anonymousUser") ? null : username;
	}



}
