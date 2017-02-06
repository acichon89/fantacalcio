package com.javangarda.fantacalcio.user.application.internal.impl;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.model.Role;
import com.javangarda.fantacalcio.user.application.model.User;
import com.javangarda.fantacalcio.user.application.model.UserStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class SimpleUserFactoryTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private SimpleUserFactory simpleUserFactory = new SimpleUserFactory();

    @Test
    public void shouldCreateNotConfirmedUserWithId() {
        //given:
        RegistrationUserDTO dto = new RegistrationUserDTO();
        dto.setEmail("john@doe.com");
        dto.setPassword("123");
        dto.setConfirmedPassword("123");
        dto.setFullName("John Doe");
        Mockito.when(passwordEncoder.encode("123")).thenReturn("123hash");
        //when:
        User createdUser = simpleUserFactory.create(dto);
        //then:
        Assert.assertTrue(createdUser.hasStatus(UserStatus.NOT_CONFIRMED));
        Assert.assertNotNull(createdUser.getId());
        Assert.assertEquals(1, createdUser.getRoles().size());
        Assert.assertEquals(Role.ROLE_USER, createdUser.getRoles().iterator().next());
        Assert.assertEquals("123hash", createdUser.getPassword());
    }
}