package com.javangarda.fantacalcio.user.application.internal.impl;

import org.junit.Assert;
import org.junit.Test;

import com.javangarda.fantacalcio.user.application.data.SignUpSocialConnection;
import com.javangarda.fantacalcio.user.application.model.entity.User;
import com.javangarda.fantacalcio.user.application.model.value.Role;
import com.javangarda.fantacalcio.user.application.model.value.UserStatus;

public class UserFactoryImplTest {

	private UserFactoryImpl userFactoryImpl = new UserFactoryImpl();
	
	@Test
	public void shouldCreateNotConfirmedUserWithId() {
		//given:
		//when:
		User createdUser = userFactoryImpl.create();
		//then:
		Assert.assertEquals(UserStatus.NOT_CONFIRMED, createdUser.getStatus());
		Assert.assertNotNull(createdUser.getId());
		Assert.assertEquals(1, createdUser.getRoles().size());
		Assert.assertEquals(Role.ROLE_USER, createdUser.getRoles().iterator().next());
	}
	
	@Test
	public void shouldCreateConfirmedUserWithId() {
		//given:
		String fullName = "John Smith";
		String email = "johm@smith.co.uk";
		SignUpSocialConnection connection = new SignUpSocialConnection(email, fullName);
		//when:
		User createdUser = userFactoryImpl.create(connection);
		//then:
		Assert.assertEquals(UserStatus.CONFIRMED, createdUser.getStatus());
		Assert.assertNotNull(createdUser.getId());
		Assert.assertEquals(1, createdUser.getRoles().size());
		Assert.assertEquals(Role.ROLE_USER, createdUser.getRoles().iterator().next());
		Assert.assertEquals(fullName, createdUser.getFullName());
		Assert.assertEquals(email, createdUser.getEmail());
	}

}
