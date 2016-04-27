package com.javangarda.fantacalcio.util.convert;

import java.util.Collections;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public abstract class AbstractDozerConverter<S, D> implements Converter<S, D> {

	private Mapper mapper;
	private Class<S> sourceClass;
	private Class<D> destinationClass;
	
	public AbstractDozerConverter(Class<S> sourceClass, Class<D> destinationClass) {
		this.mapper=getMapper();
		this.sourceClass=sourceClass;
		this.destinationClass=destinationClass;
	}
	
	protected Mapper getMapper() {
		return new DozerBeanMapper(getMappingFiles());
	}

	protected List<String> getMappingFiles() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public D convertTo(S s) {
		return mapper.map(s, destinationClass);
	}

	@Override
	public S convertFrom(D d) {
		return mapper.map(d, sourceClass);
	}

}
