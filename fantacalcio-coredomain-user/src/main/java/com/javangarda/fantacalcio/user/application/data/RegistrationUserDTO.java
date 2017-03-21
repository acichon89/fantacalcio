package com.javangarda.fantacalcio.user.application.data;

import com.javangarda.fantacalcio.commons.validation.EqualFields;
import com.javangarda.fantacalcio.commons.validation.RepositoryFieldUnique;
import com.javangarda.fantacalcio.commons.validation.StrongPassword;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@Data
@EqualFields(baseField = "password", matchField = "confirmedPassword", message = "validation.passwordsnotequal")
@EqualsAndHashCode
public class RegistrationUserDTO {

	@NotBlank
	@Email
	@Size(min = 3, max = 50)
	@RepositoryFieldUnique(query = "SELECT COUNT(*) FROM users WHERE email = ?", message = "validation.email.alreadyregistered")
	private String email;
	@NotBlank
	@Size(min = 3, max = 50)
	private String fullName;
	@NotBlank
	@Size(min = 8, max = 50)
	@StrongPassword
	private String password;
	@NotBlank
	@Size(min = 8, max = 50)
	private String confirmedPassword;
}
