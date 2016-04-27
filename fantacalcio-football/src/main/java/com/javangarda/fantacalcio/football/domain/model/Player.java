package com.javangarda.fantacalcio.football.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.javangarda.fantacalcio.football.domain.values.PlayerPosition;
import com.javangarda.fantacalcio.util.entities.DefaultEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Player extends DefaultEntity<String>{

	@Getter @Setter
	private String fullName;
	@ManyToOne(fetch=FetchType.LAZY)
	@Getter @Setter
	private Club club;
	@Enumerated(EnumType.STRING)
	@Getter @Setter
	private PlayerPosition position;
	@Getter @Setter
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime dateOfBirth;
}
