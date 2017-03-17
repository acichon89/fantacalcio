package com.javangarda.fantacalcio.user.infrastructure.port.adapter.http.api;

import com.javangarda.fantacalcio.commons.http.ResponseDTO;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.Assert.assertNotNull;


public class MethodArgumentNotValidExceptionHandlerTest {

    private MethodArgumentNotValidExceptionHandler handler = new MethodArgumentNotValidExceptionHandler();

    @Test
    public void shouldCreateDTO(){
        //given:
        BindingResult result = Mockito.mock(BindingResult.class);
        Mockito.when(result.getFieldError()).thenReturn(null);
        Mockito.when(result.getGlobalError()).thenReturn(null);
        MethodArgumentNotValidException exception = Mockito.mock(MethodArgumentNotValidException.class);
        Mockito.when(exception.getBindingResult()).thenReturn(result);
        //when:
        ResponseDTO responseDTO = handler.methodArgumentNotValidException(exception);
        //then:
        assertNotNull(responseDTO);
        Mockito.verify(exception).getBindingResult();
    }
}