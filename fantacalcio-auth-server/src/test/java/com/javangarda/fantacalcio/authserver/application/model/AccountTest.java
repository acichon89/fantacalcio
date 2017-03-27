package com.javangarda.fantacalcio.authserver.application.model;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountTest {

    @Test
    public void shouldBeAllowed(){
        //given:
        Account account = Mockito.spy(new Account());
        Mockito.when(account.getStatus()).thenReturn(UserStatus.CONFIRMED);
        //when:
        boolean allowed = account.isAllowed();
        //then:
        assertTrue(allowed);
    }

    @Test
    public void shouldNotBeAllowed(){
        //given:
        Account account = Mockito.spy(new Account());
        Mockito.when(account.getStatus()).thenReturn(UserStatus.BANNED);
        //when:
        boolean allowed = account.isAllowed();
        //then:
        assertFalse(allowed);
    }

}