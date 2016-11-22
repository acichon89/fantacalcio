package com.javangarda.fantacalcio.extras;

public interface Converter<S,D> {

	D convertTo(S s);
	
	S convertFrom(D d);
}
