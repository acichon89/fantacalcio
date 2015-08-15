package com.javangarda.fantacalcio.util.convert;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Config.class)
public class AbstractDozerConverterIntegrationTest {

	@Autowired
	private Converter<User, UserDto> userConverter;

	@Test
	public void test() {
		Assert.assertNotNull(userConverter);
		User user = new User();
		user.setId(123l);
		user.setName("Jan Kowalski");
		user.setPassword("secret");
		
		UserDto userDto = userConverter.convertTo(user);
		Assert.assertNotNull(userDto);
		Assert.assertEquals(user.getName(), userDto.getName());
		Assert.assertEquals(userDto.getId(), user.getId());
		
		User janKowalski = userConverter.convertFrom(userDto);
		
		Assert.assertEquals(janKowalski.getName(), userDto.getName());
		Assert.assertEquals(userDto.getId(), janKowalski.getId());
	}

}

@Configuration
class Config {

	@Bean
	public Converter<User, UserDto> converter() {
		return new UserUserDtoConverter();
	}
}

class UserUserDtoConverter extends AbstractDozerConverter<User, UserDto>{

	public UserUserDtoConverter() {
		super(User.class, UserDto.class);
	}

	@Override
	protected List<String> getMappingFiles() {
		return Collections.EMPTY_LIST;
	}

}