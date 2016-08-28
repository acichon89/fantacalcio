package com.javangarda.fantacalcio.user.application.internal.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDto;
import com.javangarda.fantacalcio.user.application.event.EmailNotFoundException;
import com.javangarda.fantacalcio.user.application.internal.AccessTokenGenerator;
import com.javangarda.fantacalcio.user.application.internal.UserFactory;
import com.javangarda.fantacalcio.user.application.internal.UserRepository;
import com.javangarda.fantacalcio.user.application.model.entity.User;
import com.javangarda.fantacalcio.util.convert.Converter;


@RunWith(MockitoJUnitRunner.class)
public class TransactionalUserServiceTest {

	@Mock
	private UserRepository userRepository;
	@Mock
	private Converter<RegistrationUserDto, User> userRegistrationConverter;
	@Mock
	private UserFactory userFactory;
	@Mock
	private AccessTokenGenerator accessTokenGenerator;
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private TransactionalUserService transactionalUserService = Mockito.spy(new TransactionalUserService());
	
	@Test
	public void shouldAssignTokenAfterGenerate() throws EmailNotFoundException {
		//given:
		User user = new User();
		Mockito.when(userRepository.findByEmail("john@doe.com")).thenReturn(user);
		Mockito.when(accessTokenGenerator.createConfirmEmailToken()).thenReturn("qqq");
		//when:
		String token = transactionalUserService.assignActivationToken("john@doe.com");
		//then:
		Assert.assertEquals("qqq", token);
		Assert.assertEquals("qqq", user.getConfirmEmailToken());
	}
	
	@Test(expected=EmailNotFoundException.class)
	public void shouldThrowErrorWhenNoEmailFoundInRepo() throws EmailNotFoundException{
		//given:
		Mockito.when(userRepository.findByEmail("john@doe.com")).thenThrow(EmailNotFoundException.class);
		//when:
		String token = transactionalUserService.assignActivationToken("john@doe.com");
	}

}
