package com.javangarda.fantacalcio.user.domain.model;

import javax.persistence.Entity;

import com.javangarda.fantacalcio.util.entities.DefaultEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class User extends DefaultEntity<String> {

	@Getter @Setter
	private String email;
	@Getter @Setter
	private String fullName;
	@Getter @Setter
	private String password;
}
