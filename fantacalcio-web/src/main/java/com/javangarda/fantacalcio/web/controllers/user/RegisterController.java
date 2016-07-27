package com.javangarda.fantacalcio.web.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDto;
import com.javangarda.fantacalcio.user.application.event.DuplicateEmailException;
import com.javangarda.fantacalcio.user.application.gateway.UserGateway;
import com.javangarda.fantacalcio.util.validate.AbstractValidator;
import com.javangarda.fantacalcio.util.validate.DataNotValidException;

@RestController
public class RegisterController {

	@Autowired
	private AbstractValidator<RegistrationUserDto> validator;
	@Autowired
	private UserGateway userGateway;
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public ResponseEntity<RegistrationUserDto> registerUser(@RequestBody RegistrationUserDto registrationUserDto) throws DataNotValidException, DuplicateEmailException{
		validator.validate(registrationUserDto);
		userGateway.registerUser(registrationUserDto);
		return new ResponseEntity<>(registrationUserDto, HttpStatus.OK);
	}
}