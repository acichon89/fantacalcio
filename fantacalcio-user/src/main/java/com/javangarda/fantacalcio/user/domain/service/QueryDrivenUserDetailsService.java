package com.javangarda.fantacalcio.user.domain.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.javangarda.fantacalcio.user.domain.repository.UserRepository;

public class QueryDrivenUserDetailsService implements UserDetailsService{

	private UserRepository userRepository;
	
	public QueryDrivenUserDetailsService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}

}
