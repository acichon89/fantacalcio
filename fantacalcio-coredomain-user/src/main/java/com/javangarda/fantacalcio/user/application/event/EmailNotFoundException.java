package com.javangarda.fantacalcio.user.application.event;

public class EmailNotFoundException extends Exception {

	private final String email;
	
	public EmailNotFoundException(String email){
		super("Email '"+email+"' not found in the system");
		this.email=email;
	}

}
