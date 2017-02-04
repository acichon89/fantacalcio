package com.javangarda.fantacalcio.user.application.gateway.impl;


/*import com.fasterxml.jackson.databind.ObjectMapper;
import com.javangarda.fantacalcio.user.IntegrationConfig;
import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.internal.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.cloud.stream.converter.AbstractFromMessageConverter;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.integration.annotation.BridgeFrom;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        EventDrivenUserGatewayIntegrationTestContext.class
}, initializers = {
        DisableAutowireRequireInitializer.class
})*/
public class EventDrivenUserGatewaySagaTest {

/*
    @Autowired
    private EventDrivenUserGateway gateway;
    @Autowired
    private UserService userService;
    @Autowired
    private QueueChannel sendingConfirmationEmailQueue;

    @After
    public void clean(){
        Mockito.reset(userService);
        sendingConfirmationEmailQueue.clear();
    }

    @Test
    public void testWorkflowWithConnection() throws InterruptedException{
        //given:
        String token = "bhjgdfbgjdfb";
        String email = "john@doe.com";
        String id = "aaa-bbb";
        Mockito.when(userService.registerUser(Mockito.any(RegistrationUserDTO.class))).thenReturn(id);
        Mockito.when(userService.assignActivationToken(email, id)).thenReturn(token);
        RegistrationUserDTO dto = create(email, true);
        //when:
        gateway.registerUser(dto);
        Thread.currentThread().sleep(800);
        //then:
        Mockito.verify(userService).registerUser(dto);
        Mockito.verify(userService).assignActivationToken(email, id);
        Message<?> message = sendingConfirmationEmailQueue.receive(0);
        String emailPayload = (String) message.getPayload();
        Assert.assertEquals(email, emailPayload);
        Assert.assertEquals(token, message.getHeaders().get("activationToken"));
    }

    private RegistrationUserDTO create(String email, boolean withConnection){
        RegistrationUserDTO dto = new RegistrationUserDTO();
        dto.setConfirmedPassword("aaaa");
        dto.setEmail(email);
        dto.setFullName("Abdul Srabdul");
        dto.setPassword("aaaa");
        return dto;
    }
}

@Configuration
@Import(value={IntegrationConfig.class} )
class EventDrivenUserGatewayIntegrationTestContext {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public List<AbstractFromMessageConverter> converters(){
        return Collections.emptyList();
    }

    @Bean
    public List<GenericConverter> genericConverters(){
        return Collections.emptyList();
    }

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }

    @Bean
    @BridgeFrom(value="sendingConfirmationEmailChannel")
    public QueueChannel sendingConfirmationEmailQueue(){
        return new QueueChannel(1);
    }
}
class DisableAutowireRequireInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        // Register the AutowiredAnnotationBeanPostProcessor while initalizing
        // the context so we get there before any @Autowire resolution happens
        // We set the "requiredParameterValue" so that @Autowire fields are not
        // required to be resolved. Very useful for a test context
        GenericApplicationContext ctx = (GenericApplicationContext) applicationContext;
        ctx.registerBeanDefinition(AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME,
                BeanDefinitionBuilder
                        .rootBeanDefinition(AutowiredAnnotationBeanPostProcessor.class)
                        .addPropertyValue("requiredParameterValue", false)
                        .getBeanDefinition());

    }
*/

}