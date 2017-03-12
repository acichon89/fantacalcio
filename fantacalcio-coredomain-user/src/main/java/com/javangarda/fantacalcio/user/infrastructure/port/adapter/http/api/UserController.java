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

	@RequestMapping(value = "/confirmEmail", method = RequestMethod.POST)
	public ResponseEntity<FantaCalcioUser> confirmEmail(@RequestParam(value = "token") String activationToken) {
		return userGateway.confirmEmail(activationToken).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseEntity<String> changePassword(@RequestBody @Validated ChangePasswordDTO changePasswordDTO, Principal principal) {
		userGateway.changePassword(changePasswordDTO, principal.getName());
		return ResponseEntity.ok().body("OK");
	}

	@RequestMapping(value = "/startResetPasswordProcedure", method = RequestMethod.POST)
	public ResponseEntity<String> startResetPasswordConfirmation(Principal principal) {
		userGateway.startResetPasswordProcedure(principal.getName());
		return ResponseEntity.ok().body("OK");
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ResponseEntity<FantaCalcioUser> resetPassword(@RequestBody @Validated ResetPasswordDTO resetPasswordDTO) {
		return userGateway.resetPassword(resetPasswordDTO).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/ban", method = RequestMethod.POST)
	public ResponseEntity<String> banUser(@RequestParam(value="email") String email){
		userGateway.ban(email);
		return ResponseEntity.ok().body("OK");
	}

	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public ResponseEntity<Principal> currentPrincipal(Principal principal){
		return ResponseEntity.ok(principal);
	}
}