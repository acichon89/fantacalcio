package com.javangarda.fantacalcio.football.domain.gateways.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.integration.annotation.BridgeFrom;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javangarda.fantacalcio.football.application.FootballDomainContext;
import com.javangarda.fantacalcio.football.application.FootballIntegrationContext;
import com.javangarda.fantacalcio.football.domain.data.ClubDTO;
import com.javangarda.fantacalcio.football.domain.events.DuplicateClubNameException;
import com.javangarda.fantacalcio.football.domain.services.ClubService;
import com.javangarda.fantacalcio.util.contexts.RootApplicationProfilesContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestConfig.class, initializers={DisableAutowireRequireInitializer.class})
public class EventDrivenFootballGatewayIntegrationTest {
	
	@Autowired
	private EventDrivenFootballGateway gateway;
	@Autowired
	private ClubService clubService;
	@Autowired
	private QueueChannel testChannel;
	
	@Test
	public void shouldDelegateToServiceWhileCreatingClub() throws DuplicateClubNameException {
		String value = gateway.createClub("Juve");
		Assert.assertEquals("Abc", value);
		Mockito.verify(clubService, Mockito.times(1)).createClub("Juve");
	}
	
	@Test
	public void should() throws DuplicateClubNameException {
		ClubDTO clubDTO = ClubDTO.builder().active(true).name("AC Milan").id("aaa-bbb").build();
		gateway.updateClub(clubDTO);
		Message<?> message = testChannel.receive(0);
		Assert.assertNotNull(message);
		ClubDTO payload = (ClubDTO) message.getPayload();
		Assert.assertEquals(clubDTO, payload);
	}
}

@Configuration()
@Import(value={FootballDomainContext.class,FootballIntegrationContext.class, RootApplicationProfilesContext.class})
class TestConfig {
	
	@Bean
	public ClubService clubService() throws DuplicateClubNameException{
		ClubService clubService = Mockito.mock(ClubService.class);
		Mockito.when(clubService.createClub(Mockito.anyString())).thenReturn("Abc");
		return clubService;
	}
	
	@Bean
	@BridgeFrom(value="updateClubChannel")
	public QueueChannel testChannel(){
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

}