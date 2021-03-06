package com.javangarda.fantacalcio.user.application.model;

import com.javangarda.fantacalcio.commons.entities.VersionedDefaultEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
@ToString(exclude = "password")
public class User extends VersionedDefaultEntity<String> {

	@Getter
	private String email;
	@Column(name = "tmp_email")
	@Getter
	private String tmpEmail;
	@Getter
	@Column(name = "full_name")
	private String fullName;
	@Getter @Setter
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
	private String confirmEmailToken;

	@Column(name = "reset_password_token")
	@Setter
	private String resetPasswordToken;

	public User() {super();};

	public User(String id){
		super(id);
	}

	public void register(String fullName, String password) {
		addRole(Role.ROLE_USER);
		this.status=UserStatus.NOT_CONFIRMED;
		this.fullName=fullName;
		this.password=password;
	}

	public void confirmEmail() {
		this.email=this.tmpEmail;
		this.tmpEmail=null;
		this.confirmEmailToken=null;
		if(hasStatus(UserStatus.NOT_CONFIRMED)){
			this.status=UserStatus.CONFIRMED;
		}
	}

	public void assignEmailToBeConfirmed(String email, String token){
		this.tmpEmail=email;
		this.confirmEmailToken=token;
	}

	public boolean hasConfirmationEmailToken(String token){
		return this.confirmEmailToken == null ? token == null : this.confirmEmailToken.equals(token);
	}

	public boolean addRole(Role role) {
		return this.roles.add(role);
	}

	public boolean hasRole(Role role) {
		return this.roles.contains(role);
	}
	
	public boolean hasStatus(UserStatus status) {
		return status==null ? this.status==null : this.status.equals(status);
	}

	public void ban() {
		this.status=UserStatus.BANNED;
	}

	public void changePassword(String newPassword, boolean clearToken){
		this.password=newPassword;
		if(clearToken){
			this.resetPasswordToken=null;
		}
	}
}
