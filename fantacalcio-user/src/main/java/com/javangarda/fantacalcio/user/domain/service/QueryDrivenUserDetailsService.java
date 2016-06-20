package com.javangarda.fantacalcio.user.domain.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.domain.model.User;
import com.javangarda.fantacalcio.user.domain.repository.UserRepository;
import com.javangarda.fantacalcio.user.domain.value.FantaCalcioUser;
import com.javangarda.fantacalcio.user.domain.value.Role;

@Component
public class QueryDrivenUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User foundInRepoUser = userRepository.findByEmail(username);	//email is login
		if(foundInRepoUser==null) {
			throw new UsernameNotFoundException("No user found with username: " + username);
		}
		return convert(foundInRepoUser);
	}
	
	private UserDetails convert(User user) {
		FantaCalcioUser fcuDTO = new FantaCalcioUser(user.getEmail(), user.getPassword(), getAuthorities(user));
		fcuDTO.setSocialMediaTypes(user.getSocialMediaTypes());
		return fcuDTO;
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(User user) {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(); //TODO JAVA8 
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.name()));
		}
		return authorities;
	}

}
