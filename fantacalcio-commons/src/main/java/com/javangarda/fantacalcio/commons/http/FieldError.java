package com.javangarda.fantacalcio.commons.http;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class FieldError {

    @Getter
    private String path;
    @Getter
    private String code;
    @Getter
    private String message;

}
