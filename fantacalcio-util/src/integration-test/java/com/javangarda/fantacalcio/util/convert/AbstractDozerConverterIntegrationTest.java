package com.javangarda.fantacalcio.util.convert;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import commotest.TestUser;
import commotest.TestUserDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Config.class)
public class AbstractDozerConverterIntegrationTest {

	@Autowired
	private Converter<TestUser, TestUserDto> userConverter;

	@Test
	public void test() {
		Assert.assertNotNull(userConverter);
		TestUser user = new TestUser();
		user.setId(123l);
		user.setName("Jan Kowalski");
		user.setPassword("secret");
		
		TestUserDto userDto = userConverter.convertTo(user);
		Assert.assertNotNull(userDto);
		Assert.assertEquals(user.getName(), userDto.getName());
		Assert.assertEquals(userDto.getId(), user.getId());
		
		TestUser janKowalski = userConverter.convertFrom(userDto);
		
		Assert.assertEquals(janKowalski.getName(), userDto.getName());
		Assert.assertEquals(userDto.getId(), janKowalski.getId());
	}

}

@Configuration
class Config {

	@Bean
	public Converter<TestUser, TestUserDto> converter() {
		return new UserUserDtoConverter();
	}
}

class UserUserDtoConverter extends AbstractDozerConverter<TestUser, TestUserDto>{

	public UserUserDtoConverter() {
		super(TestUser.class, TestUserDto.class);
	}

	@Override
	protected List<String> getMappingFiles() {
		return Collections.EMPTY_LIST;
	}

}