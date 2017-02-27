package com.javangarda.fantacalcio.user.infrastructure.port.adapter.http.api;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class BadRequestResponseDTO extends ResponseDTO {

    @Getter
    private Set<FieldError> fieldErrors;
    @Getter
    private Set<GlobalError> globalErrors;

    public BadRequestResponseDTO(BindingResult result) {
        super(400, "bad_request");
        handleFieldErrors(result.getFieldErrors());
        handleGlobalErrors(result.getGlobalErrors());
    }

    public void handleFieldErrors(Collection<org.springframework.validation.FieldError> _fieldErrors) {
        if(_fieldErrors==null){
            return;
        }
        this.fieldErrors = _fieldErrors.stream().map(fe -> new FieldError(fe.getField(), fe.getCode(), fe.getDefaultMessage())).collect(Collectors.toSet());
    }

    public void handleGlobalErrors(Collection<ObjectError> _objectErrors) {
        if(_objectErrors==null){
            return;
        }
        this.globalErrors = _objectErrors.stream().map(oe -> new GlobalError(oe.getCode(), oe.getDefaultMessage())).collect(Collectors.toSet());
    }

}
@AllArgsConstructor
@EqualsAndHashCode
class FieldError {

    @Getter
    private String path;
    @Getter
    private String code;
    @Getter
    private String message;

}
@AllArgsConstructor
@EqualsAndHashCode
class GlobalError {
    @Getter
    private String code;
    @Getter
    private String message;
}