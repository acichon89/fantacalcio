package com.javangarda.fantacalcio.gamerules.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.javangarda.fantacalcio.util.entities.DefaultEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="simple_game_rules")
public class SimpleGameRule extends DefaultEntity<Integer>{

	@Column
	@Getter @Setter
	private String ruleName;
}
