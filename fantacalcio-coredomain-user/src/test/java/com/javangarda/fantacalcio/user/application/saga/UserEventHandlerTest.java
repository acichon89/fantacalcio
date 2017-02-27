package com.javangarda.fantacalcio.user.application.saga;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.event.UserCommandSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class UserEventHandlerTest {

    @Mock
    private UserCommandSender userCommandSender;

    @InjectMocks
    private UserEventHandler userEventHandler = Mockito.spy(new UserEventHandler());

    @Test
    public void shouldDelegateWhileHandlingRegistration() {
        //given:
        RegistrationUserDTO dto = new RegistrationUserDTO();
        dto.setEmail("john@doe.com");
        String id = "aaa-zzz";
        //when:
        userEventHandler.handleUserRegisterEvent(dto, id);
        //then:
        Mockito.verify(userCommandSender).createActivationEmailToken("john@doe.com", "aaa-zzz");
    }
}