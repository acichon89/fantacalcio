package com.javangarda.fantacalcio.user.application.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.javangarda.fantacalcio.user.application.data.SignUpSocialConnection;
import com.javangarda.fantacalcio.user.application.internal.SocialConnectionResolver;
import com.javangarda.fantacalcio.user.application.internal.UserFactory;
import com.javangarda.fantacalcio.user.application.internal.UserRepository;
import com.javangarda.fantacalcio.user.application.model.entity.User;

@Component
@Transactional
public class CustomSocialSignIn implements ConnectionSignUp {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SocialConnectionResolver socialConnectionResolver;
	@Autowired
	private UserFactory userFactory;
	
	@Override
	public String execute(Connection<?> connection) {
		SignUpSocialConnection establishedSocialConnection = socialConnectionResolver.create(connection);
		if(establishedSocialConnection==null){
			return null;
		}
		User foundUser = userRepository.findByEmail(establishedSocialConnection.getEmail());
		if(foundUser!=null){
			foundUser.setFullName(establishedSocialConnection.getFullName());
			return foundUser.getEmail();
		}
		User newUser = userFactory.create(establishedSocialConnection);
		userRepository.save(newUser);
		return newUser.getEmail();
	}
	
}

