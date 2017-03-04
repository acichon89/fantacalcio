package com.javangarda.fantacalcio.user.application.data;

import com.javangarda.fantacalcio.commons.validation.EqualFields;
import com.javangarda.fantacalcio.commons.validation.PasswordCheck;
import com.javangarda.fantacalcio.commons.validation.StrongPassword;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@Data
@EqualFields(baseField = "newPassword", matchField = "confirmNewPassword", message = "validation.passwordsnotequal")
public class ChangePasswordDTO {

    @PasswordCheck(message = "validation.password.incorrect", query = "SELECT password FROM users WHERE email= ?")
    private String oldPassword;
    @NotBlank
    @Size(min = 8, max = 50)
    @StrongPassword
    private String newPassword;
    @NotBlank
    private String confirmNewPassword;
}
