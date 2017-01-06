package com.javangarda.fantacalcio.user.application.data;

import lombok.Data;

import java.util.Optional;

@Data
public class ChangePasswordDTO {

    private String newPassword;
    private String confirmNewPassword;
    private Optional<String> oldPassword;
    private String userId;
}
