package com.javangarda.fantacalcio.football.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages="com.javangarda.fantacalcio.football.domain", excludeFilters = @ComponentScan.Filter(value = Configuration.class, type=FilterType.ANNOTATION))
public class FootballDomainContext {

}
