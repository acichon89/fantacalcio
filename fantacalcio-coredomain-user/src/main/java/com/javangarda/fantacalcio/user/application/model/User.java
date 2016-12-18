package com.javangarda.fantacalcio.user.application.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.javangarda.fantacalcio.extras.DefaultEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
public class User extends DefaultEntity<String> {

	@Getter
	private String email;
	@Getter
	private String fullName;
	@Getter
	private String password;
	
	@ElementCollection(targetClass = Role.class)
	@CollectionTable(name = "users_roles_rel", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	@Getter
	private Set<Role> roles = new HashSet<Role>();
	
	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@Column(name="confirm_email_token")
	@Setter
	private String confirmEmailToken;

	public User() {};
	public User(String id){
		super(id);
	}

	public void register(String email, String fullName, String password) {
		this.roles.add(Role.ROLE_USER);
		this.status=UserStatus.NOT_CONFIRMED;
		this.email=email;
		this.fullName=fullName;
		this.password=password;
	}

	public boolean addRole(Role role) {
		return this.roles.add(role);
	}
	
	public boolean hasStatus(UserStatus status) {
		return status==null ? this.status==null : this.status.equals(status);
	}
}
