package com.javangarda.fantacalcio.mail.infrastructure.port.adapter.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javangarda.fantacalcio.mail.application.gateway.EmailGateway;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EmailController {

	@Autowired
	private EmailGateway emailGateway;
	
	@RequestMapping(value="/sendConfirmAccountEmail", method=RequestMethod.GET)
	public ResponseEntity<Void> sendEmail(@RequestParam("email") String email, @RequestParam("token") String token){
		emailGateway.sendActivationEmail(email, token);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Void> handleAnyError(Exception e){
		e.printStackTrace();	
		log.error("e", e);
		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
