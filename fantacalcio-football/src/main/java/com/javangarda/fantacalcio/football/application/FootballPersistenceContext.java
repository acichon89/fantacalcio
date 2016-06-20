package com.javangarda.fantacalcio.football.application;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.javangarda.fantacalcio.util.contexts.RootPersistenceContext;

@Configuration
@Import(RootPersistenceContext.class)
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.javangarda.fantacalcio.football.domain.repositories"}, entityManagerFactoryRef="footballEntityManagerFactory")
public class FootballPersistenceContext {

	@Autowired
	@Qualifier("dataSourceProperties")
	private Properties datasourceProperties;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean footballEntityManagerFactory(DataSource dataSource, Environment env, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setPackagesToScan("com.javangarda.fantacalcio.football.domain.model");
        entityManagerFactoryBean.setJpaProperties(datasourceProperties);
        return entityManagerFactoryBean;
	}
	
	@Bean
    public JpaTransactionManager userTransactionManager(DataSource dataSource, Environment env, JpaVendorAdapter jpaVendorAdapter) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(footballEntityManagerFactory(dataSource, env, jpaVendorAdapter).getNativeEntityManagerFactory());
        return transactionManager;
    }
}
