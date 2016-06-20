package com.javangarda.fantacalcio.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.security.SocialUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class RegisterController {
	
	private ProviderSignInUtils magic;
	private AuthenticationManager manager;
	
	@Autowired
	public RegisterController (ConnectionFactoryLocator connectionFactoryLocator,
            UsersConnectionRepository connectionRepository) {
		this.magic = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
		
		Connection<Facebook> fb_connection = (Connection<Facebook>) magic.getConnectionFromSession(request);
		UserProfile userProfile = fb_connection.fetchUserProfile();
		model.addAttribute("name", fb_connection.getApi().userOperations().getUserProfile().getName());//i get the name
		model.addAttribute("id", fb_connection.getApi().userOperations().getUserProfile().getId()); //i get the id
		model.addAttribute("email", fb_connection.getApi().userOperations().getUserProfile().getEmail()); // email and all other values are always null
		model.addAttribute(userProfile);
		
		SocialUser socialUser = new SocialUser(fb_connection.getApi().userOperations().getUserProfile().getEmail(), null, null);
		
		Authentication auth = new UsernamePasswordAuthenticationToken(socialUser, null);
		Authentication a2 = manager.authenticate(auth);
		if(a2.isAuthenticated()){
			SecurityContextHolder.getContext().setAuthentication(a2);
		}
		
		model.addAttribute("cos", SecurityContextHolder.getContext().getAuthentication().getName());
		return "user/welcome";
	}
}
