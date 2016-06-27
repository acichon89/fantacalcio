package com.javangarda.fantacalcio.user.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.domain.model.User;
import com.javangarda.fantacalcio.user.domain.repository.UserRepository;
import com.javangarda.fantacalcio.util.convert.Converter;

@Component
@Transactional
public class QueryDrivenUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private Converter<User, UserDetails> userDetailsConverter;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User foundInRepoUser = userRepository.findByEmail(username);	//email is login
		if(foundInRepoUser==null) {
			throw new UsernameNotFoundException("No user found with username: " + username);
		}
		return userDetailsConverter.convertTo(foundInRepoUser);
	}
	

}
