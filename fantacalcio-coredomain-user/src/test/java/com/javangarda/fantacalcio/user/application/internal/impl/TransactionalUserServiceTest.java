package com.javangarda.fantacalcio.user.application.internal.impl;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.internal.AccessTokenGenerator;
import com.javangarda.fantacalcio.user.application.internal.UserFactory;
import com.javangarda.fantacalcio.user.application.internal.UserRepository;
import com.javangarda.fantacalcio.user.application.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TransactionalUserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserFactory userFactory;
    @Mock
    private AccessTokenGenerator accessTokenGenerator;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private TransactionalUserService transactionalUserService = Mockito.spy(new TransactionalUserService());

    @Test
    public void shouldStoreInRepoWhileRegister(){
        //given:
        RegistrationUserDTO dto = new RegistrationUserDTO();
        dto.setFullName("John");
        dto.setEmail("john@wick.com");
        User user = new User("aa-bb-cc");
        Mockito.when(userFactory.create(dto)).thenReturn(user);
        //when:
        String id = transactionalUserService.registerUser(dto);
        //then:
        assertEquals("aa-bb-cc", id);
    }

}