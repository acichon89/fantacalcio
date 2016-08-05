package com.javangarda.fantacalcio.user.application.internal.impl;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.FantaCalcioUser;
import com.javangarda.fantacalcio.user.application.internal.CurrentUserProvider;

@Component
public class CurrentUserProviderImpl implements CurrentUserProvider {

	private static final String ANONYMOUS_USER_NAME = "anonymousUser";
	
	@Override
	public Optional<FantaCalcioUser> getCurrentLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return username.equals(ANONYMOUS_USER_NAME) ? Optional.empty() : Optional.of((FantaCalcioUser)authentication.getPrincipal());
	}

}
