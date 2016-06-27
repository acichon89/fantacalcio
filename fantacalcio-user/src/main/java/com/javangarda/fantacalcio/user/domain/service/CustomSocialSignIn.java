package com.javangarda.fantacalcio.user.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.javangarda.fantacalcio.user.domain.factories.UserFactory;
import com.javangarda.fantacalcio.user.domain.model.User;
import com.javangarda.fantacalcio.user.domain.repository.UserRepository;
import com.javangarda.fantacalcio.user.domain.value.EstablishedUserSocialConnection;

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
		EstablishedUserSocialConnection establishedSocialConnection = socialConnectionResolver.create(connection);
		User foundUser = userRepository.findByEmail(establishedSocialConnection.getEmail());
		if(foundUser!=null){
			foundUser.getSocialMediaTypes().add(establishedSocialConnection.getSocialMediaType());
			return foundUser.getEmail();
		}
		User newUser = userFactory.create(establishedSocialConnection);
		userRepository.save(newUser);
		return newUser.getEmail();
	}
	
}

