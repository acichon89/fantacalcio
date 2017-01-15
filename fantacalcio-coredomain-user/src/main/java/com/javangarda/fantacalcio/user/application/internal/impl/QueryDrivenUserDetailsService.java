package com.javangarda.fantacalcio.user.application.internal.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.FantaCalcioUser;
import com.javangarda.fantacalcio.user.application.internal.UserRepository;
import com.javangarda.fantacalcio.user.application.model.User;

import lombok.extern.slf4j.Slf4j;

@Component
@Transactional
@Slf4j
public class QueryDrivenUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username).map(FantaCalcioUser::new).orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + username));
	}
}
