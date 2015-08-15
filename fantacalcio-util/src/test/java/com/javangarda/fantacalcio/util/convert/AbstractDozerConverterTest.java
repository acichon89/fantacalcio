package com.javangarda.fantacalcio.util.convert;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AbstractDozerConverterTest {

	@Mock
	private Mapper mapper;
	
	@InjectMocks
	private AbstractDozerConverter<Donkey, DonkeyDto> converter = new DonkeyConverter();
	
	@Test
	public void test() {
		Donkey donkey = new Donkey();
		converter.convertTo(donkey);
		Mockito.verify(mapper, Mockito.times(1)).map(donkey, DonkeyDto.class);
	}

}

class Donkey {
	private String name;
}

class DonkeyDto {
	private String dummyName;
}

class DonkeyConverter extends AbstractDozerConverter<Donkey, DonkeyDto> {

	public DonkeyConverter() {
		super(Donkey.class, DonkeyDto.class);
	}

	@Override
	protected List<String> getMappingFiles() {
		return Collections.EMPTY_LIST;
	}
	
}