package com.javangarda.fantacalcio.user.application.internal.impl;

import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.javangarda.fantacalcio.user.application.data.FantaCalcioUser;
import com.javangarda.fantacalcio.user.application.model.entity.User;
import com.javangarda.fantacalcio.user.application.model.value.UserStatus;

public class CurrentUserProviderImplTest {

	private CurrentUserProviderImpl currentUserProviderImpl = new CurrentUserProviderImpl();
	
	@After
	public void cleanSecurityContextHolder() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}
	
	@Test
	public void shouldReturnNullWhenNobodyIsLogged() {
		//given:
		Authentication anonymousAuth = Mockito.mock(Authentication.class);
		Mockito.when(anonymousAuth.getName()).thenReturn("anonymousUser");
		SecurityContextHolder.getContext().setAuthentication(anonymousAuth);
		//when:
		Optional<FantaCalcioUser> fuc = currentUserProviderImpl.getCurrentLoggedInUser();
		//then:
		Assert.assertFalse(fuc.isPresent());
	}
	
	@Test
	public void shouldReturnLoggedUser() {
		//given:
		FantaCalcioUser user = createFantaCalcioUser();
		Authentication customAuth = Mockito.mock(Authentication.class);
		Mockito.when(customAuth.getName()).thenReturn("fantaCalcioUser");
		Mockito.when(customAuth.getPrincipal()).thenReturn(user);
		SecurityContextHolder.getContext().setAuthentication(customAuth);
		//when:
		Optional<FantaCalcioUser> fucOptional = currentUserProviderImpl.getCurrentLoggedInUser();
		//then:
		Assert.assertTrue(fucOptional.isPresent());
		FantaCalcioUser authUser = fucOptional.get();
		Assert.assertEquals(user, authUser);
	}

	private FantaCalcioUser createFantaCalcioUser() {
		User user = new User();
		user.setStatus(UserStatus.CONFIRMED);
		user.setEmail("abc@abc.com");
		user.setFullName("John Doe");
		user.setId("aaa-bbb-ccc");
		user.setPassword("aaaaaaaaaaaaaaa");
		return new FantaCalcioUser(user, null);
	}
	
}
