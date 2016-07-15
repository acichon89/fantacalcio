package com.javangarda.fantacalcio.user.application.internal.impl;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.internal.RandomStringGenerator;

@Component
public class RandomStringGeneratorImpl implements RandomStringGenerator{

	@Override
	public String generateRandomAlphaNumericString(int length) {
		char[] symbols;
		StringBuilder tmp = new StringBuilder();
		for (char ch = '0'; ch <= '9'; ++ch){
			tmp.append(ch);
		}
		for (char ch = 'a'; ch <= 'z'; ++ch){
			tmp.append(ch);
		}
		symbols = tmp.toString().toCharArray();
		final Random random = new Random();
		final char[] buf = new char[length];
		for (int idx = 0; idx < buf.length; ++idx){
			buf[idx] = symbols[random.nextInt(symbols.length)];
		}
		return new String(buf);
	}

}
