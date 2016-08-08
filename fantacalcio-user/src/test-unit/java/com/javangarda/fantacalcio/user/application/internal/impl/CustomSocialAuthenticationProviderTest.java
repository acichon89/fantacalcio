package com.javangarda.fantacalcio.user.application.internal.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.social.security.SocialUserDetailsService;


@RunWith(MockitoJUnitRunner.class)
public class CustomSocialAuthenticationProviderTest {

	@Mock
	private UsersConnectionRepository usersConnectionRepository;
	@Mock
	private SocialUserDetailsService userDetailsService;
	
	private CustomSocialAuthenticationProvider customSocialAuthenticationProvider = Mockito.spy(new CustomSocialAuthenticationProvider(usersConnectionRepository, userDetailsService));
	
	@Test
	public void supportsTest() {
		//given:
		SocialAuthenticationToken socialAuthToken = new SocialAuthenticationToken(mockConnection(), null);
		Assert.assertTrue(customSocialAuthenticationProvider.supports(socialAuthToken.getClass()));
		Assert.assertFalse(customSocialAuthenticationProvider.supports(userDetailsService.getClass()));
	}
	
	
	private Connection mockConnection() {
		Connection connection = Mockito.mock(Connection.class);
		ConnectionData connectionData = Mockito.mock(ConnectionData.class);
		Mockito.when(connectionData.getAccessToken()).thenReturn("4234324-234-32423423-asd");
		Mockito.when(connectionData.getExpireTime()).thenReturn(System.currentTimeMillis() + 99999);
		Mockito.when(connectionData.getProviderId()).thenReturn("twitter");
		Mockito.when(connection.createData()).thenReturn(connectionData);
		
		return connection;
	}

}
