package com.javangarda.fantacalcio;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptTest {

	@Test
	public void test() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		System.out.println(encoder.encode("test"));
	}

}
