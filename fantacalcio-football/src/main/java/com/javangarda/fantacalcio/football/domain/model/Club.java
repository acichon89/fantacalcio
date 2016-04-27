package com.javangarda.fantacalcio.football.domain.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.javangarda.fantacalcio.util.entities.DefaultEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Club extends DefaultEntity<String>{

	@Column(unique=true)
	@Getter @Setter
	private String name;
	@Getter @Setter
	private boolean active;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="club")
	@Getter @Setter
	private Set<Player> players;
}
