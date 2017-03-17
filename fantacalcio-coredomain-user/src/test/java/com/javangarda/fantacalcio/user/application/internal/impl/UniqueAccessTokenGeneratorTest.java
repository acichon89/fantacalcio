package com.javangarda.fantacalcio.user.application.internal.impl;

import com.javangarda.fantacalcio.user.application.internal.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UniqueAccessTokenGeneratorTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UniqueAccessTokenGenerator uniqueAccessTokenGenerator = Mockito.spy(new UniqueAccessTokenGenerator());

    @Test
    public void shouldGenerateRandomConfirmEmailTokenAsLongAsIsNotUnique() {
        //given:
        Mockito.when(userRepository.countUserWithConfirmEmailToken(Mockito.anyString())).thenReturn(1).thenReturn(1).thenReturn(0);
        //when:
        String token = uniqueAccessTokenGenerator.createConfirmEmailToken();
        Assert.assertNotNull("ccc", token);
        Mockito.verify(userRepository, Mockito.times(3)).countUserWithConfirmEmailToken(Mockito.anyString());
    }

    @Test
    public void shouldGenerateRandomResetPasswordTokenAsLongAsIsNotUnique() {
        //given:
        Mockito.when(userRepository.countUserWithResetPasswordToken(Mockito.anyString())).thenReturn(1).thenReturn(1).thenReturn(0);
        //when:
        String token = uniqueAccessTokenGenerator.createResetPasswordToken();
        Assert.assertNotNull("ccc", token);
        Mockito.verify(userRepository, Mockito.times(3)).countUserWithResetPasswordToken(Mockito.anyString());
    }
}