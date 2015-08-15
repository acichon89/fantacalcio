package com.javangarda.fantacalcio.util.convert;

public interface Converter<S,D> {

	D convertTo(S s);
	
	S convertFrom(D d);
}
