package com.javangarda.fantacalcio.user.application.event;

public class PasswordNotMatchException extends Exception {
    public PasswordNotMatchException() {
        super("Password not match");
    }
}
