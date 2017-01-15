package com.javangarda.fantacalcio.user.application.data;

import lombok.Data;

import java.util.Optional;

@Data
public class ChangePasswordDTO {

    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
    private String userEmail;
}
