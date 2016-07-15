package com.javangarda.fantacalcio.web.validators;

public interface GuiDtoValidator<F> {

	void validate (F form) throws GuiDtoNotValidException;
}
