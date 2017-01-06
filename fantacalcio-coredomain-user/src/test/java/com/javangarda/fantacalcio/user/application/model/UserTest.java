package com.javangarda.fantacalcio.user.application.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void shouldBeNotConfirmedAndHasDefaultRoleAfterRegister(){
        //given:
        User user = new User("1");
        //when:
        user.register("John Smith", "test");
        //then:
        assertTrue(user.hasStatus(UserStatus.NOT_CONFIRMED));
        assertTrue(user.hasRole(Role.ROLE_USER));
    }

    @Test
    public void should() {
        //given:
        User user = new User("1");
        user.register("John Smith", "test");
        user.assignEmailToBeConfirmed("johnsmith@google.com", "abcd");
        //when:
        user.confirmEmail();
        //then:
        assertEquals("johnsmith@google.com", user.getEmail());
        assertNull(user.getTmpEmail());
        assertTrue(user.hasConfirmationEmailToken(null));
        assertTrue(user.hasStatus(UserStatus.CONFIRMED));
    }
}