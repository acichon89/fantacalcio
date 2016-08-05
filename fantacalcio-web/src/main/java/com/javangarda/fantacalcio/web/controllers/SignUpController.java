package com.javangarda.fantacalcio.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SignUpController {
	
	private ProviderSignInUtils magic;
	
	@Autowired
	public SignUpController (ConnectionFactoryLocator connectionFactoryLocator,
            UsersConnectionRepository connectionRepository) {
		this.magic = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
		
		Connection connection =  magic.getConnectionFromSession(request);
		UserProfile userProfile = connection.fetchUserProfile();
		log.debug("KEY="+connection.getKey().getProviderId());
		log.debug("KEY="+connection.getKey().getProviderUserId());
		
		return "user/register";
	}
	
}
