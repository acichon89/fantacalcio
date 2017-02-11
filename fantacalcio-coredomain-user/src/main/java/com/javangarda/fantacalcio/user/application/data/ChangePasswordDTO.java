package com.javangarda.fantacalcio.user.application.data;

import com.javangarda.fantacalcio.user.infrastructure.port.adapter.validation.StrongPassword;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@Data
public class ChangePasswordDTO {

    private String oldPassword;
    @NotBlank
    @Size(min = 8, max = 50)
    @StrongPassword
    private String newPassword;
    @NotBlank
    @Size(min = 8, max = 50)
    @StrongPassword
    private String confirmNewPassword;
}
