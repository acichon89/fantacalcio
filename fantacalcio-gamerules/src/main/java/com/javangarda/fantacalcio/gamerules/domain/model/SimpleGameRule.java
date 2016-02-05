package com.javangarda.fantacalcio.gamerules.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.javangarda.fantacalcio.util.entities.DefaultEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class SimpleGameRule extends DefaultEntity<Integer>{

	@Column
	@Getter @Setter
	private String ruleName;
}
