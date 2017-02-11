package com.javangarda.fantacalcio.user.application.gateway.impl;

import com.javangarda.fantacalcio.user.application.data.ChangePasswordDTO;
import com.javangarda.fantacalcio.user.application.data.FantaCalcioUser;
import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.data.ResetPasswordDTO;
import com.javangarda.fantacalcio.user.application.event.EmailCommandSender;
import com.javangarda.fantacalcio.user.application.event.UserEventPublisher;
import com.javangarda.fantacalcio.user.application.internal.UserService;
import com.javangarda.fantacalcio.user.application.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class EventDrivenUserGatewayTest {

    @Mock
    private UserService userService;
    @Mock
    private UserEventPublisher userEventPublisher;
    @Mock
    private EmailCommandSender emailCommandSender;

    @InjectMocks
    private EventDrivenUserGateway eventDrivenUserGateway = Mockito.spy(new EventDrivenUserGateway());

    @Test
    public void shouldRegisterUserAndPublishEvent() {
        //given:
        RegistrationUserDTO registerDto = new RegistrationUserDTO();
        registerDto.setFullName("John Doe");
        registerDto.setPassword("pass");
        Mockito.when(userService.registerUser(registerDto)).thenReturn("123l");
        //when:
        eventDrivenUserGateway.registerUser(registerDto);
        //then:
        Mockito.verify(userService).registerUser(registerDto);
        Mockito.verify(userEventPublisher).publishUserCreated(registerDto, "123l");
    }

    @Test
    public void shouldSendCommandAfterAssignActivationToken()  {
        //given:
        String email = "abc@abc.pl";
        String id = "321l";
        Mockito.when(userService.assignActivationToken(email, id)).thenReturn(Optional.of("tokenTokenToken"));
        //when:
        eventDrivenUserGateway.startConfirmationEmailProcedure(email, id);
        //then:
        Mockito.verify(emailCommandSender).sendConfirmationEmail(email, "tokenTokenToken");
    }

    @Test
    public void shouldNotSendCommandAfterAssignActivationToken()  {
        //given:
        String email = "abc@abc.pl";
        String id = "321l";
        Mockito.when(userService.assignActivationToken(email, id)).thenReturn(Optional.empty());
        //when:
        eventDrivenUserGateway.startConfirmationEmailProcedure(email, id);
        //then:
        Mockito.verify(emailCommandSender, Mockito.never()).sendConfirmationEmail(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void shouldReturnUserAfterConfirmation() {
        //given:
        String email = "boo@dog.com";
        String token = "abcdefg";
        Mockito.when(userService.confirmEmail(token)).thenReturn(Optional.of(create(email, token)));
        //when:
        Optional<FantaCalcioUser> user = eventDrivenUserGateway.confirmEmail(token);
        //then:
        Assert.assertTrue(user.isPresent());
        Mockito.verify(userEventPublisher).publishEmailConfirmed(email);
    }

    @Test
    public void shouldNotReturnUserIfConfirmationTokenIsInvalid(){
        //given:
        String token = "abcdefg";
        Mockito.when(userService.confirmEmail(token)).thenReturn(Optional.empty());
        //when:
        Optional<FantaCalcioUser> user = eventDrivenUserGateway.confirmEmail(token);
        //then:
        Assert.assertFalse(user.isPresent());
        Mockito.verify(userEventPublisher, Mockito.never()).publishEmailConfirmed(Mockito.anyString());
    }

    @Test
    public void shouldDelegateWhileChangingPassword() {
        //given:
        ChangePasswordDTO dto = new ChangePasswordDTO();
        dto.setNewPassword("wakaWaka");
        //when:
        eventDrivenUserGateway.changePassword(dto, "boo@dog.com");
        //then:
        Mockito.verify(userService).changePassword("wakaWaka", "boo@dog.com");
    }

    @Test
    public void shouldDelegateWhileResetingPassword() {
        //given:
        ResetPasswordDTO dto = new ResetPasswordDTO();
        dto.setNewPassword("wakaWaka");
        dto.setResetPasswordToken("token123");
        //when:
        eventDrivenUserGateway.resetPassword(dto);
        //then:
        Mockito.verify(userService).resetPassword("wakaWaka", "token123");
    }

    @Test
    public void shouldDelegateAndSendEventWhileBanning() {
        //given:
        String email = "naughty@user.com";
        //when:
        eventDrivenUserGateway.ban(email);
        //then:
        Mockito.verify(userService).banUser(email);
        Mockito.verify(userEventPublisher).publishUserBanned(email);
    }

    private FantaCalcioUser create(String email, String token) {
        User user = new User("aaa-bbb-ccc");
        user.register("John Doe", "pass#hash");
        user.assignEmailToBeConfirmed(email, token);
        user.confirmEmail();
        return new FantaCalcioUser(user);
    }
}