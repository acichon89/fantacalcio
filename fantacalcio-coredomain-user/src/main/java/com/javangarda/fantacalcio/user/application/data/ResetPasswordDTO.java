package com.javangarda.fantacalcio.user.application.data;

import com.javangarda.fantacalcio.user.infrastructure.port.adapter.validation.EqualFields;
import com.javangarda.fantacalcio.user.infrastructure.port.adapter.validation.RepositoryFieldExists;
import com.javangarda.fantacalcio.user.infrastructure.port.adapter.validation.StrongPassword;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualFields(baseField = "newPassword", matchField = "confirmNewPassword", message = "xxx yyy zzz")
public class ResetPasswordDTO {

    @NotBlank
    @Size(min = 8, max = 50)
    @StrongPassword
    private String newPassword;
    @NotBlank
    @Size(min = 8, max = 50)
    private String confirmNewPassword;
    @NotNull
    @RepositoryFieldExists(query = "SELECT COUNT(*) FROM users WHERE reset_password_token = ?", message = "dummy_code")
    private String resetPasswordToken;
}
