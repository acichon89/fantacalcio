package com.javangarda.fantacalcio.user.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;

import com.javangarda.fantacalcio.user.domain.value.Role;
import com.javangarda.fantacalcio.user.domain.value.SocialMediaType;
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
	@ElementCollection(targetClass = Role.class)
	@CollectionTable(name = "users_roles_rel", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	@Getter @Setter
	private Set<Role> roles = new HashSet<Role>();
	@ElementCollection(targetClass = SocialMediaType.class)
	@CollectionTable(name = "users_socialmediatypes_rel", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "social_media_type", nullable = false)
	@Enumerated(EnumType.STRING)
	@Getter @Setter
	private Set<SocialMediaType> socialMediaTypes = new HashSet<SocialMediaType>();
}
