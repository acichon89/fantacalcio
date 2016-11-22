package com.javangarda.fantacalcio.user.application.data;

import java.util.HashSet;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.javangarda.fantacalcio.user.application.model.User;
import com.javangarda.fantacalcio.user.application.model.UserStatus;

public class FantaCalcioUser extends org.springframework.security.core.userdetails.User {

    public FantaCalcioUser(User user) {
    	super(user.getEmail(),user.getPassword(),user.hasStatus(UserStatus.CONFIRMED), 
    			user.hasStatus(UserStatus.CONFIRMED), user.hasStatus(UserStatus.CONFIRMED), 
    			user.hasStatus(UserStatus.CONFIRMED), 
    			user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toCollection(HashSet::new)));
    }
}
