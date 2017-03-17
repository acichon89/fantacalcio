package com.javangarda.fantacalcio.user.application.saga;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.event.UserCommandSender;
import com.javangarda.fantacalcio.user.application.internal.AuthCommandSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class UserSagaTest {

    @Mock
    private UserCommandSender userCommandSender;
    @Mock
    private AuthCommandSender authCommandSender;

    @InjectMocks
    private UserSaga userSaga = Mockito.spy(new UserSaga());

    @Test
    public void shouldDelegateWhileHandlingRegistration() {
        //given:
        RegistrationUserDTO dto = new RegistrationUserDTO();
        dto.setEmail("john@doe.com");
        String id = "aaa-zzz";
        //when:
        userSaga.handleUserRegisterEvent(dto, id);
        //then:
        Mockito.verify(userCommandSender).createActivationEmailToken("john@doe.com", "aaa-zzz");
    }

    @Test
    public void foo(){
        //given:
        //when:
        userSaga.handleUserBannedEvent("aaa@ban.com");
        //then:
        Mockito.verify(authCommandSender).removeAccessToken("aaa@ban.com");
    }
}