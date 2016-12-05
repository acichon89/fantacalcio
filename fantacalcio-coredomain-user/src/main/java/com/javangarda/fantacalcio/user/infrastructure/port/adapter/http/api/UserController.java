package com.javangarda.fantacalcio.user.infrastructure.port.adapter.http.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.event.DuplicateEmailException;
import com.javangarda.fantacalcio.user.application.gateway.UserGateway;

@RestController
public class UserController {

	@Autowired
	private UserGateway userGateway;
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public ResponseEntity<String> registerUser(@RequestBody RegistrationUserDTO registrationUserDto) throws DuplicateEmailException {
		userGateway.registerUser(registrationUserDto);
		return ResponseEntity.ok().body("OK");
	}
	
	@RequestMapping(value="/foo", method=RequestMethod.GET)
	public String foo(){
		return "bar";
	}
}
