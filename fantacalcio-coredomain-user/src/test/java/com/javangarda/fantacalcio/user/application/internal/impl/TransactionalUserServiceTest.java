package com.javangarda.fantacalcio.user.application.internal.impl;

import com.javangarda.fantacalcio.user.application.data.FantaCalcioUser;
import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.internal.AccessTokenGenerator;
import com.javangarda.fantacalcio.user.application.internal.UserFactory;
import com.javangarda.fantacalcio.user.application.internal.UserRepository;
import com.javangarda.fantacalcio.user.application.model.User;
import com.javangarda.fantacalcio.user.application.model.UserStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.*;

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

    @Test
    public void shouldAssignActivationToken(){
        //given:
        User user = Mockito.spy(new User("123"));
        Mockito.when(userRepository.findOne("123")).thenReturn(Optional.of(user));
        Mockito.when(accessTokenGenerator.createConfirmEmailToken()).thenReturn("aaa-bbb-ccc");
        //when:
        Optional<String> token = transactionalUserService.assignActivationToken("johny@doe.com", "123");
        //then:
        assertTrue(token.isPresent());
        assertEquals("aaa-bbb-ccc", token.get());
        Mockito.verify(user, Mockito.times(1)).assignEmailToBeConfirmed("johny@doe.com", "aaa-bbb-ccc");
    }

    @Test
    public void shouldReturnEmptyWhileAssignActivationToken(){
        //given:
        Mockito.when(userRepository.findOne("123")).thenReturn(Optional.empty());
        //when:
        Optional<String> token = transactionalUserService.assignActivationToken("johny@doe.com", "123");
        //then:
        assertFalse(token.isPresent());
        Mockito.verify(accessTokenGenerator, Mockito.never()).createConfirmEmailToken();
    }

    @Test
    public void shouldAssignResetPasswordToken(){
        //given:
        User user = new User("33333");
        Mockito.when(userRepository.findByEmail("a@b.com")).thenReturn(Optional.of(user));
        Mockito.when(accessTokenGenerator.createResetPasswordToken()).thenReturn("baba");
        //when:
        Optional<String> token = transactionalUserService.assignResetPasswordToken("a@b.com");
        //then:
        assertTrue(token.isPresent());
        assertEquals("baba", token.get());
        Mockito.verify(accessTokenGenerator).createResetPasswordToken();
    }

    @Test
    public void shouldNotAssignResetPasswordToken(){
        //given:
        Mockito.when(userRepository.findByEmail("example@test.com")).thenReturn(Optional.empty());
        //when:
        Optional<String> token = transactionalUserService.assignResetPasswordToken("example@test.com");
        //then:
        assertFalse(token.isPresent());
    }

    @Test
    public void shouldConfirmEmailAndTransformToDto(){
        //given:
        User user = Mockito.mock(User.class);
        Mockito.when(user.hasStatus(UserStatus.CONFIRMED)).thenReturn(true);
        Mockito.when(user.getPassword()).thenReturn("aaa");
        Mockito.when(user.getEmail()).thenReturn("aaa@bbb.com");
        Mockito.when(userRepository.findByConfirmEmailToken("abc")).thenReturn(Optional.of(user));
        //when:
        Optional<FantaCalcioUser> fcu = transactionalUserService.confirmEmail("abc");
        //then:
        assertTrue(fcu.isPresent());
        Mockito.verify(user).confirmEmail();
        assertEquals("aaa@bbb.com", fcu.get().getUsername());
    }

    @Test
    public void shouldReturnEmpty(){
        //given:
        Mockito.when(userRepository.findByConfirmEmailToken("avv")).thenReturn(Optional.empty());
        //when:
        Optional<FantaCalcioUser> fcu = transactionalUserService.confirmEmail("avv");
        //then:
        assertFalse(fcu.isPresent());
    }

    @Test
    public void shouldEncodePassword(){
        //given:
        Mockito.when(passwordEncoder.encode("aaa")).thenReturn("a-a-a");
        User user = Mockito.mock(User.class);
        Mockito.when(userRepository.findByEmail("email@example.com")).thenReturn(Optional.of(user));
        //when:
        transactionalUserService.changePassword("aaa", "email@example.com");
        //then:
        Mockito.verify(user).changePassword("a-a-a", false);
    }

    @Test
    public void shouldResetPassword(){
        //given:
        Mockito.when(passwordEncoder.encode("aaa")).thenReturn("a-a-a");
        User user = Mockito.mock(User.class);
        Mockito.when(user.hasStatus(UserStatus.CONFIRMED)).thenReturn(true);
        Mockito.when(user.getPassword()).thenReturn("aaa");
        Mockito.when(user.getEmail()).thenReturn("aaa@bbb.com");
        Mockito.when(userRepository.findByResetPasswordToken("token123")).thenReturn(Optional.of(user));
        //when:
        Optional<FantaCalcioUser> fcu = transactionalUserService.resetPassword("aaa", "token123");
        //then:
        assertTrue(fcu.isPresent());
        Mockito.verify(user).changePassword("a-a-a", true);
        assertEquals("aaa@bbb.com", fcu.get().getUsername());
    }

    @Test
    public void shouldBanUser(){
        //given:
        User user = Mockito.mock(User.class);
        Mockito.when(userRepository.findByEmail("email@test.com")).thenReturn(Optional.of(user));
        //when:
        transactionalUserService.banUser("email@test.com");
        //then:
        Mockito.verify(user).ban();
    }
}