package com.javangarda.fantacalcio.user.application.data;

import com.javangarda.fantacalcio.user.infrastructure.port.adapter.validation.RepositoryFieldUnique;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Data
public class RegistrationUserDTO {

	@NotBlank
	@Email
	//@Range(min = 3, max = 60)
	@RepositoryFieldUnique(query = "SELECT COUNT(*) FROM users WHERE email = ?", message = "aaa bbb ccc")
	private String email;
	@NotBlank
	//@Range(min = 3, max = 100)
	private String fullName;
	@NotBlank
	//@Range(min = 8, max = 100)
	private String password;
	private String confirmedPassword;
}
