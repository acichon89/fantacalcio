package com.javangarda.fantacalcio.user.domain.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.javangarda.fantacalcio.user.domain.model.User;
import com.javangarda.fantacalcio.user.domain.repository.UserRepository;
import com.javangarda.fantacalcio.user.domain.value.ReturnedCOnnectionUserDTO;
import com.javangarda.fantacalcio.user.domain.value.Role;
import com.javangarda.fantacalcio.user.domain.value.SocialMediaType;

@Component
@Transactional
public class CustomSocialSignIn implements ConnectionSignUp {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String execute(Connection<?> connection) {
		ReturnedCOnnectionUserDTO userDTO = createDTO(connection);
		User foundUser = userRepository.findByEmail(userDTO.getEmail());
		if(foundUser!=null){
			foundUser.getSocialMediaTypes().add(userDTO.getSocialMediaType());
			return foundUser.getEmail();
		}
		User newUser = new User();
		newUser.setId(UUID.randomUUID().toString());
		newUser.setEmail(userDTO.getEmail());
		newUser.getSocialMediaTypes().add(userDTO.getSocialMediaType());
		newUser.setFullName(userDTO.getFullName());
		newUser.getRoles().add(Role.ROLE_USER);
		userRepository.save(newUser);
		return newUser.getEmail();
	}
	
	private ReturnedCOnnectionUserDTO createDTO(Connection<?> connection) {
		ReturnedCOnnectionUserDTO user = new ReturnedCOnnectionUserDTO();
		Object api = connection.getApi();
		if(api instanceof Facebook) {
			Facebook fapi = (Facebook) api;
			user.setEmail(fapi.userOperations().getUserProfile().getEmail());
			user.setSocialMediaType(SocialMediaType.FACEBOOK);
			user.setFullName(fapi.userOperations().getUserProfile().getFirstName()+" "+fapi.userOperations().getUserProfile().getLastName());
		} else if(api instanceof Twitter) {
			Twitter tapi = (Twitter) api;
			user.setEmail(connection.fetchUserProfile().getEmail());
			user.setSocialMediaType(SocialMediaType.TWITTER);
			user.setFullName(connection.getDisplayName());
		}
		return user;
	}
}

