package com.javangarda.fantacalcio.user.application.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class DelegatingSocialUserDetailsService implements SocialUserDetailsService {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		return (SocialUserDetails) userDetailsService.loadUserByUsername(userId);
	}

}
