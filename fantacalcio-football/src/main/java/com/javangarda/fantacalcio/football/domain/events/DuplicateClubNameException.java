package com.javangarda.fantacalcio.football.domain.events;

import com.javangarda.fantacalcio.util.i18n.MessageLocalizable;

import lombok.Getter;

public class DuplicateClubNameException extends Exception implements MessageLocalizable{

	private static final long serialVersionUID = -6431169360535911751L;
	
	@Getter
	private String duplicatedClubName;
	
	public DuplicateClubNameException(String duplicatedName){
		super("Trying to save club with non-unique name.");
		this.duplicatedClubName=duplicatedName;
	}

	@Override
	public String getMessageKey() {
		return "football.exception.DuplicateClubNameException.messageKey";
	}
	
}
