package com.javangarda.fantacalcio.user.application.internal.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.connect.Connection;

import com.javangarda.fantacalcio.user.application.internal.SocialConnectionResolver;
import com.javangarda.fantacalcio.user.application.internal.UserFactory;
import com.javangarda.fantacalcio.user.application.internal.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class CustomSocialSignInTest {

	@Mock
	private UserRepository userRepository;
	@Mock
	private SocialConnectionResolver socialConnectionResolver;
	@Mock
	private UserFactory userFactory;
	
	@InjectMocks
	private CustomSocialSignIn customSocialSignIn = Mockito.spy(new CustomSocialSignIn());
	
	@Test
	public void shouldReturnNullWhenThereIsNoSignUpConnection() {
		//given:
		Connection connection = Mockito.mock(Connection.class);
		Mockito.when(socialConnectionResolver.create(connection)).thenReturn(null);
		//when:
		String email = customSocialSignIn.execute(connection);
		Assert.assertNull(email);
	}

}
