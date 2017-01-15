package com.javangarda.fantacalcio.user.infrastructure.port.adapter.http.api;

import com.javangarda.fantacalcio.user.application.data.ChangePasswordDTO;
import com.javangarda.fantacalcio.user.application.data.FantaCalcioUser;
import com.javangarda.fantacalcio.user.application.data.ResetPasswordDTO;
import com.javangarda.fantacalcio.user.infrastructure.port.adapter.http.secure.CurrentUserProvider;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.gateway.UserGateway;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserGateway userGateway;
	@Autowired
	private CurrentUserProvider currentUserProvider;

	@InitBinder
	public void dataBinding(WebDataBinder binder, HttpServletRequest request) {
		binder.bind(new MutablePropertyValues(Collections.singletonMap("userEmail",currentUserProvider.getCurrentUser().flatMap(this::getEmail).orElse("") )));
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<String> registerUser(@RequestBody @Validated RegistrationUserDTO registrationUserDto)  {
		userGateway.registerUser(registrationUserDto);
		return ResponseEntity.ok().body("OK");
	}

	@RequestMapping(value = "/confirmEmail", method = RequestMethod.GET)
	public ResponseEntity<FantaCalcioUser> confirmEmail(@RequestParam(value = "token", required = true) String activationToken) {
		return userGateway.confirmEmail(activationToken).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseEntity<String> changePassword(@RequestBody @Validated ChangePasswordDTO changePasswordDTO) {
		userGateway.changePassword(changePasswordDTO);
		return ResponseEntity.ok().body("OK");
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ResponseEntity<String> resetPassword(@RequestBody @Validated ResetPasswordDTO resetPasswordDTO) {
		userGateway.resetPassword(resetPasswordDTO);
		return ResponseEntity.ok().body("OK");
	}

	private Optional<String> getEmail(FantaCalcioUser user){
		return Optional.of(user.getUsername());
	}
}