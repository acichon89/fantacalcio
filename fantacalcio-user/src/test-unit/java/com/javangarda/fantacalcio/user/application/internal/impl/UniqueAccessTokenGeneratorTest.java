package com.javangarda.fantacalcio.user.application.internal.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.javangarda.fantacalcio.user.application.internal.RandomStringGenerator;
import com.javangarda.fantacalcio.user.application.internal.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UniqueAccessTokenGeneratorTest {

	@Mock
	private UserRepository userRepository;
	@Mock
	private RandomStringGenerator randomStringGenerator;

	@InjectMocks
	private UniqueAccessTokenGenerator uniqueAccessTokenGenerator = Mockito.spy(new UniqueAccessTokenGenerator());
	
	@Test
	public void shouldGenerateRandomTokenAsLongAsIsNotUnique() {
		//given:
		Mockito.when(randomStringGenerator.generateRandomAlphaNumericString(25)).thenReturn("aaa", "bbb", "ccc");
		Mockito.when(userRepository.countUserWithConfirmEmailToken("aaa")).thenReturn(1);
		Mockito.when(userRepository.countUserWithConfirmEmailToken("bbb")).thenReturn(1);
		Mockito.when(userRepository.countUserWithConfirmEmailToken("ccc")).thenReturn(0);
		//when:
		String token = uniqueAccessTokenGenerator.createConfirmEmailToken();
		Assert.assertEquals("ccc", token);
		Mockito.verify(randomStringGenerator, Mockito.times(3)).generateRandomAlphaNumericString(25);
	}

}
