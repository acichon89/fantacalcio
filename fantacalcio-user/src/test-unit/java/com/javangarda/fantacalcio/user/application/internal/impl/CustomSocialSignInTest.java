package com.javangarda.fantacalcio.user.application.internal.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.connect.Connection;

import com.javangarda.fantacalcio.user.application.data.SignUpSocialConnection;
import com.javangarda.fantacalcio.user.application.internal.SocialConnectionResolver;
import com.javangarda.fantacalcio.user.application.internal.UserFactory;
import com.javangarda.fantacalcio.user.application.internal.UserRepository;
import com.javangarda.fantacalcio.user.application.model.entity.User;
import com.javangarda.fantacalcio.user.application.model.value.UserStatus;

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
		//then:
		Assert.assertNull(email);
	}
	
	@Test
	public void shouldFindAndUpdateUserWithConnectionData() {
		//given:
		String email = "john.doe@gmail.com";
		User user = Mockito.mock(User.class);
		Mockito.when(user.getEmail()).thenReturn(email);
		Mockito.when(userRepository.findByEmail(Mockito.eq(email))).thenReturn(user);
		Connection connection = Mockito.mock(Connection.class);
		Mockito.when(socialConnectionResolver.create(connection)).thenReturn(create(email));
		//when:
		String returnedEmail = customSocialSignIn.execute(connection);
		//then:
		Assert.assertEquals(email, returnedEmail);
		Mockito.verify(userRepository).findByEmail(email);
		Mockito.verify(user).setFullName("John Doe");
		Mockito.verify(user).setStatus(UserStatus.CONFIRMED);
	}
	
	@Test
	public void shouldCreateAndSaveUserBasedOnConnection() {
		//given:
		String email = "john.doe@gmail.com";
		Mockito.when(userRepository.findByEmail(email)).thenReturn(null);
		Connection connection = Mockito.mock(Connection.class);
		SignUpSocialConnection signUpConnection = create(email);
		Mockito.when(socialConnectionResolver.create(connection)).thenReturn(signUpConnection);
		User dummyUser = Mockito.mock(User.class);
		Mockito.when(dummyUser.getEmail()).thenReturn(email);
		Mockito.when(userFactory.create(Mockito.any(SignUpSocialConnection.class))).thenReturn(dummyUser);
		//when:
		String returnedEmail = customSocialSignIn.execute(connection);
		//then:
		Assert.assertEquals(email, returnedEmail);
		Mockito.verify(userFactory).create(signUpConnection);
		Mockito.verify(userRepository).save(dummyUser);
	}

	private SignUpSocialConnection create(String email){
		return new SignUpSocialConnection(email,"John Doe");
	}
}
