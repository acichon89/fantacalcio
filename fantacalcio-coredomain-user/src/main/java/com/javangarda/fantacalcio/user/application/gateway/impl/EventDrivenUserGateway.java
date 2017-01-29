package com.javangarda.fantacalcio.user.application.gateway.impl;

import com.javangarda.fantacalcio.user.application.data.ChangePasswordDTO;
import com.javangarda.fantacalcio.user.application.data.FantaCalcioUser;
import com.javangarda.fantacalcio.user.application.data.ResetPasswordDTO;
import com.javangarda.fantacalcio.user.application.event.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.gateway.UserGateway;
import com.javangarda.fantacalcio.user.application.internal.UserService;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Component
@Slf4j
public class EventDrivenUserGateway implements UserGateway {

	@Autowired
	private UserEventPublisher userEventPublisher;
	@Autowired
	private EmailCommandSender emailCommandSender;
	@Autowired
	private UserService userService;

	@Override
	public void registerUser(RegistrationUserDTO registrationUserDTO) {
		log.debug("Thread = {} @EventDrivenUserGateway::registerUser, registrationUserDto={}", Thread.currentThread().getName(), registrationUserDTO);
		String id = userService.registerUser(registrationUserDTO);
		userEventPublisher.publishUserCreated(registrationUserDTO, id);
	}

	@Override
	@ServiceActivator(inputChannel="createActivationEmailTokenChannel")
	public void startConfirmationEmailProcedure(@Payload String mail,@Header("userId") String userId) {
		log.debug("Thread = {} @EventDrivenUserGateway::startConfirmationEmailProcedure, mail={}, userId={}", Thread.currentThread().getName(), mail, userId);
		String token = userService.assignActivationToken(mail, userId);
		emailCommandSender.sendConfirmationEmail(mail, token);
	}

	@Override
	public Optional<FantaCalcioUser> confirmEmail(String activationToken) {
		Optional<FantaCalcioUser> user = userService.confirmEmail(activationToken);
		user.ifPresent(u -> userEventPublisher.publishEmailConfirmed(u.getUsername()));
		return user;
	}

	@Override
	public void changePassword(ChangePasswordDTO changePasswordDTO) {
		userService.changePassword(changePasswordDTO.getNewPassword(), changePasswordDTO.getUserEmail());
	}

	@Override
	public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
		userService.resetPassword(resetPasswordDTO.getNewPassword(), resetPasswordDTO.getEmail());
	}
}
