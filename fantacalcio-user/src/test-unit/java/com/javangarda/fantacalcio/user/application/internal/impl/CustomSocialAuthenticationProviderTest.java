package com.javangarda.fantacalcio.user.application.internal.impl;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import com.google.common.collect.Lists;


@RunWith(MockitoJUnitRunner.class)
public class CustomSocialAuthenticationProviderTest {

	@Mock
	private UsersConnectionRepository usersConnectionRepository;
	@Mock
	private SocialUserDetailsService userDetailsService;
	
	@InjectMocks
	private CustomSocialAuthenticationProvider customSocialAuthenticationProvider = Mockito.spy(new CustomSocialAuthenticationProvider(usersConnectionRepository, userDetailsService));
	
	@Test
	public void supportsTest() {
		//given:
		SocialAuthenticationToken socialAuthToken = new SocialAuthenticationToken(mockConnection(), null);
		//then:
		Assert.assertTrue(customSocialAuthenticationProvider.supports(socialAuthToken.getClass()));
		Assert.assertFalse(customSocialAuthenticationProvider.supports(userDetailsService.getClass()));
	}
	
	@Test(expected=BadCredentialsException.class)
	public void shouldThrowBadCredentialsExceptionWhenThereIsNoConnection() {
		//given:
		Connection connection = mockConnection();
		Mockito.when(usersConnectionRepository.findUserIdsWithConnection(Mockito.any(Connection.class))).thenReturn(Collections.EMPTY_LIST);
		SocialAuthenticationToken socialAuthToken = new SocialAuthenticationToken(connection, null);
		//when:
		customSocialAuthenticationProvider.authenticate(socialAuthToken);
	}
	
	@Test(expected=UsernameNotFoundException.class)
	public void shouldThrowUsernameNotFoundExceptionWhenThereIsNoUser() {
		//given:
		Connection connection = mockConnection();
		Mockito.when(usersConnectionRepository.findUserIdsWithConnection(connection)).thenReturn(Lists.newArrayList("john@doe.pl"));
		Mockito.when(userDetailsService.loadUserByUserId(Mockito.anyString())).thenReturn(null);
		SocialAuthenticationToken socialAuthToken = new SocialAuthenticationToken(connection, null);
		//when:
		customSocialAuthenticationProvider.authenticate(socialAuthToken);
	}
	
	@Test(expected=DisabledException.class)
	public void shouldThrowDisabledExceptionWhenThereIsNoUser() {
		//given:
		Connection connection = mockConnection();
		Mockito.when(usersConnectionRepository.findUserIdsWithConnection(connection)).thenReturn(Lists.newArrayList("john@doe.pl"));
		SocialUserDetails johnDoeDetails = mockUserDetails(false);
		Mockito.when(userDetailsService.loadUserByUserId("john@doe.pl")).thenReturn(johnDoeDetails);
		SocialAuthenticationToken socialAuthToken = new SocialAuthenticationToken(connection, null);
		//when:
		customSocialAuthenticationProvider.authenticate(socialAuthToken);
	}
	
	@Test
	public void shouldReturnProperToken() {
		//given:
		Connection connection = mockConnection();
		Mockito.when(usersConnectionRepository.findUserIdsWithConnection(connection)).thenReturn(Lists.newArrayList("john@doe.pl"));
		SocialUserDetails johnDoeDetails = mockUserDetails(true);
		Mockito.when(userDetailsService.loadUserByUserId("john@doe.pl")).thenReturn(johnDoeDetails);
		SocialAuthenticationToken socialAuthToken = new SocialAuthenticationToken(connection, null);
		//when:
		SocialAuthenticationToken token = (SocialAuthenticationToken) customSocialAuthenticationProvider.authenticate(socialAuthToken);
		//then:
		Assert.assertEquals(connection, token.getConnection());
		Assert.assertEquals(johnDoeDetails, token.getPrincipal());
	}
	
	private SocialUserDetails mockUserDetails(boolean isEnabled){
		SocialUserDetails userDetails = Mockito.mock(SocialUserDetails.class);
		Mockito.when(userDetails.isEnabled()).thenReturn(isEnabled);
		return userDetails;
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
