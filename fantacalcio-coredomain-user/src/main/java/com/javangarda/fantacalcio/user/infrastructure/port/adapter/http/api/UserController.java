package com.javangarda.fantacalcio.user.infrastructure.port.adapter.http.api;

import com.javangarda.fantacalcio.user.application.data.ChangePasswordDTO;
import com.javangarda.fantacalcio.user.application.data.FantaCalcioUser;
import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.data.ResetPasswordDTO;
import com.javangarda.fantacalcio.user.application.gateway.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserGateway userGateway;

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<String> registerUser(@RequestBody @Validated RegistrationUserDTO registrationUserDto)  {
		userGateway.registerUser(registrationUserDto);
		return ResponseEntity.ok().body("OK");
	}

	@RequestMapping(value = "/confirmEmail", method = RequestMethod.GET)
	public ResponseEntity<FantaCalcioUser> confirmEmail(@RequestParam(value = "token") String activationToken) {
		return userGateway.confirmEmail(activationToken).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO, Principal principal) {
		System.out.println(principal);
		System.out.println(principal.getName());
		userGateway.changePassword(changePasswordDTO);
		return ResponseEntity.ok().body("OK");
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ResponseEntity<String> resetPassword(@RequestBody @Validated ResetPasswordDTO resetPasswordDTO) {
		userGateway.resetPassword(resetPasswordDTO);
		return ResponseEntity.ok().body("OK");
	}
}