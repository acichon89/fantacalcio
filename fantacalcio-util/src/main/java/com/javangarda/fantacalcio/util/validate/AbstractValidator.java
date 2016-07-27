package com.javangarda.fantacalcio.util.validate;

public interface AbstractValidator<F> {

	void validate (F form) throws DataNotValidException;
}
