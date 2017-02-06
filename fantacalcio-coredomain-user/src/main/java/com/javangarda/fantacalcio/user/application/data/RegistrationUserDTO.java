package com.javangarda.fantacalcio.user.application.data;

import com.javangarda.fantacalcio.user.infrastructure.port.adapter.validation.RepositoryFieldUnique;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@Data
public class RegistrationUserDTO {

	@NotBlank
	@Email
	@Size(min = 3, max = 50)
	@RepositoryFieldUnique(query = "SELECT COUNT(*) FROM users WHERE email = ?", message = "aaa bbb ccc")
	private String email;
	@NotBlank
	@Size(min = 3, max = 50)
	private String fullName;
	@NotBlank
	@Size(min = 3, max = 50)
	private String password;
	@NotBlank
	@Size(min = 3, max = 50)
	private String confirmedPassword;
}
