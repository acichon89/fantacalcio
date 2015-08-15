package com.javangarda.fantacalcio.util.convert;

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

	protected abstract List<String> getMappingFiles() ;

	public D convertTo(S s) {
		D d = mapper.map(s, destinationClass);
		return d;
	}

	public S convertFrom(D d) {
		S s = mapper.map(d, sourceClass);
		return s;
	}

}
