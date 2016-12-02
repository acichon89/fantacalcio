package com.javangarda.fantacalcio.user.application.event;

public class DuplicateEmailException extends Exception {

	private final String duplicatedEmail;
	
	public DuplicateEmailException(String duplicatedEmail){
		super("Trying to register user with existing email");
		this.duplicatedEmail=duplicatedEmail;
	}

}
