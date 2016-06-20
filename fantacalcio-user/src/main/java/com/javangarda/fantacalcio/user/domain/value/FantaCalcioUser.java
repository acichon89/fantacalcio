package com.javangarda.fantacalcio.user.domain.value;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUser;

import lombok.Getter;
import lombok.Setter;

public class FantaCalcioUser extends SocialUser {

	@Getter @Setter
    private Set<SocialMediaType> socialMediaTypes;
	
    public FantaCalcioUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

}
