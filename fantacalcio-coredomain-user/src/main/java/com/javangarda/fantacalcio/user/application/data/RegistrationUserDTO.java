package com.javangarda.fantacalcio.user.application.data;

import com.javangarda.fantacalcio.user.infrastructure.port.adapter.validation.EqualFields;
import com.javangarda.fantacalcio.user.infrastructure.port.adapter.validation.RepositoryFieldUnique;
import com.javangarda.fantacalcio.user.infrastructure.port.adapter.validation.StrongPassword;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@Data
@EqualFields(baseField = "password", matchField = "confirmedPassword", message = "xxx yyy zzz")
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
	@Size(min = 8, max = 50)
	@StrongPassword
	private String password;
	@NotBlank
	@Size(min = 8, max = 50)
	private String confirmedPassword;
}
