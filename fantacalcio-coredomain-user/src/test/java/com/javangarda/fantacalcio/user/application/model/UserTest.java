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
    public void shouldRemoveTmpEmailAndChangeStatusAfterConfirmation() {
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

    @Test
    public void shouldBeBanned(){
        //given:
        User user = new User("1");
        //when:
        user.ban();
        //then:
        assertTrue(user.hasStatus(UserStatus.BANNED));
    }

    @Test
    public void shouldAssignEmailAndConfirmationToken(){
        //given:
        User user = new User("1");
        //when:
        user.assignEmailToBeConfirmed("john@doe.com", "token123");
        //then:
        assertEquals("john@doe.com", user.getTmpEmail());
        assertTrue(user.hasConfirmationEmailToken("token123"));
    }

    @Test
    public void shouldAddRole(){
        //given:
        User user = new User("1");
        //when:
        user.addRole(Role.ROLE_USER);
        //then:
        assertTrue(user.hasRole(Role.ROLE_USER));
    }

    @Test
    public void shouldChangePasswordAndDeleteToken(){
        //given:
        User user = new User("1");
        user.setPassword("pass");
        //when:
        user.changePassword("pass2", true);
        //then:
        assertEquals("pass2", user.getPassword());
    }

}