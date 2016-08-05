package com.javangarda.fantacalcio.web.controllers.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDto;
import com.javangarda.fantacalcio.user.application.event.DuplicateEmailException;
import com.javangarda.fantacalcio.user.application.gateway.UserGateway;
import com.javangarda.fantacalcio.util.validate.AbstractValidator;
import com.javangarda.fantacalcio.util.validate.DataNotValidException;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RegisterController {

	@Autowired
	private AbstractValidator<RegistrationUserDto> validator;
	@Autowired
	private UserGateway userGateway;
	
	private ProviderSignInUtils magic;
	
	@Autowired
	public RegisterController (ConnectionFactoryLocator connectionFactoryLocator,
            UsersConnectionRepository connectionRepository) {
		this.magic = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
	}
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public String registerUser(@ModelAttribute RegistrationUserDto registrationUserDto, WebRequest request) throws DataNotValidException, DuplicateEmailException{
		
		Connection connection =  magic.getConnectionFromSession(request);
		UserProfile userProfile = connection.fetchUserProfile();
		log.debug("userProfile = "+userProfile);
		log.debug("KEY="+connection.getKey().getProviderId());
		log.debug("KEY="+connection.getKey().getProviderUserId());
		registrationUserDto.setConnection(Optional.of(connection));
		validator.validate(registrationUserDto);
		userGateway.registerUser(registrationUserDto);
		return "/check";
	}
}
