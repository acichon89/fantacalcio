package com.javangarda.fantacalcio.user.application.data;

import lombok.Data;

@Data
public class ResetPasswordDTO {

    private String newPassword;
    private String confirmNewPassword;
    private String resetPasswordToken;
    private String email;
}
