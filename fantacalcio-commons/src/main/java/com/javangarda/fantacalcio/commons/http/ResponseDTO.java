package com.javangarda.fantacalcio.commons.http;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class ResponseDTO {
    @Getter
    private int status;
    @Getter
    private String messageCode;

}
