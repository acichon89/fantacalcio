package com.javangarda.fantacalcio.user.application.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.javangarda.fantacalcio.user.application.model.User;
import com.javangarda.fantacalcio.user.application.model.UserStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.stream.Collectors;

@JsonIgnoreProperties(value = {"password"})
public class FantaCalcioUser extends org.springframework.security.core.userdetails.User {

	private String id;

    public FantaCalcioUser(User user) {
    	super(user.getEmail(),user.getPassword(),user.hasStatus(UserStatus.CONFIRMED), 
    			user.hasStatus(UserStatus.CONFIRMED), user.hasStatus(UserStatus.CONFIRMED), 
    			user.hasStatus(UserStatus.CONFIRMED), 
    			user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toCollection(HashSet::new)));
    	this.id=user.getId();
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		FantaCalcioUser that = (FantaCalcioUser) o;

		return id != null ? id.equals(that.id) : that.id == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (id != null ? id.hashCode() : 0);
		return result;
	}
}
