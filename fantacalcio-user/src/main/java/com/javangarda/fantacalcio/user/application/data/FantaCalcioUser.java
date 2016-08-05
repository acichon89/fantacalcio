package com.javangarda.fantacalcio.user.application.data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

import com.javangarda.fantacalcio.user.application.model.entity.User;
import com.javangarda.fantacalcio.user.application.model.value.Role;
import com.javangarda.fantacalcio.user.application.model.value.UserStatus;

import lombok.Getter;
import lombok.Setter;

public class FantaCalcioUser extends SocialUser {

	@Getter @Setter
    private Set<String> socialMediaTypes;
	
    public FantaCalcioUser(User user, Set<String> socialMediaProviders) {
    	super(user.getEmail(),StringUtils.isBlank(user.getPassword()) ? "" : user.getPassword(),user.hasStatus(UserStatus.CONFIRMED), 
    			user.hasStatus(UserStatus.CONFIRMED), user.hasStatus(UserStatus.CONFIRMED), user.hasStatus(UserStatus.CONFIRMED), getAuthorities(user));
    	this.socialMediaTypes = socialMediaProviders;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(); //TODO JAVA8 
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.name()));
		}
		return authorities;
	}
}
