package com.javangarda.fantacalcio.user.application.internal.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.FantaCalcioUser;
import com.javangarda.fantacalcio.user.application.model.entity.User;
import com.javangarda.fantacalcio.user.application.model.value.Role;
import com.javangarda.fantacalcio.util.convert.Converter;

@Component
public class FantacalcioUserConverter implements Converter<User, UserDetails>{

	@Override
	public UserDetails convertTo(User user) {
		String password = StringUtils.isNotBlank(user.getPassword()) ? user.getPassword() : "";
		FantaCalcioUser fcuDTO = new FantaCalcioUser(user.getEmail(), password, getAuthorities(user));
		fcuDTO.setSocialMediaTypes(user.getSocialMediaTypes());
		return fcuDTO;
	}

	@Override
	public User convertFrom(UserDetails d) {
		// TODO Auto-generated method stub
		return null;
	}

	private Collection<? extends GrantedAuthority> getAuthorities(User user) {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(); //TODO JAVA8 
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.name()));
		}
		return authorities;
	}
}
