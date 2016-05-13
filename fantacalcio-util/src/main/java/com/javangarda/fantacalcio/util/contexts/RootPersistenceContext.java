package com.javangarda.fantacalcio.util.contexts;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class RootPersistenceContext {
	
	@Configuration
	@Profile(AppProfile.LOCALDEV_TEST)
	public static class LocaldevTestDataSourceConfig {
		@Bean(destroyMethod="close")
		public DataSource dataSource(Environment env) {
			HikariConfig dataSourceConfig = new HikariConfig();
	        dataSourceConfig.setDriverClassName(env.getRequiredProperty("db.driver"));
	        dataSourceConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
	        dataSourceConfig.setUsername(env.getRequiredProperty("db.username"));
	        dataSourceConfig.setPassword(env.getRequiredProperty("db.password"));
			return new HikariDataSource(dataSourceConfig);
		}
	}

	@Configuration
	@Profile(AppProfile.LOCALDEV_DEPLOY)
	public static class LocaldevDeployDataSourceConfig {
		@Bean(destroyMethod="close")
	    public DataSource dataSource() {
	        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
	        dsLookup.setResourceRef(true);
	        DataSource dataSource = dsLookup.getDataSource("jndi/jdbc/fantacalcio_db");
	        return dataSource;
	    } 
	}
	
	@Bean(name="dataSourceProperties")
	public Properties dataSourceProperties(Environment env) {
		Properties jpaProperties = new Properties();
        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
 
        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
 
        //Configures the naming strategy that is used when Hibernate creates
        //new database objects and schema elements
        jpaProperties.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("hibernate.ejb.naming_strategy"));
 
        //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
 
        //If the value of this property is true, Hibernate will format the SQL
        //that is written to the console.
        jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        
        return jpaProperties;
	}
	
	@Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter(Environment env) {
	    HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
	    hibernateJpaVendorAdapter.setShowSql(Boolean.parseBoolean(env.getRequiredProperty("hibernate.show_sql")));
	    hibernateJpaVendorAdapter.setGenerateDdl(true); //Auto creating scheme when true
	    hibernateJpaVendorAdapter.setDatabase(resolveDatabase(env));//Database type
	    return hibernateJpaVendorAdapter;
	}
	
	/*
	 * Map hibernate dialect to vendor database type
	 */
	private Database resolveDatabase(Environment env){
		String hibernateDialect = env.getRequiredProperty("hibernate.dialect");
		switch (hibernateDialect) {
		case "org.hibernate.dialect.H2Dialect":
			return Database.H2;
		case "org.hibernate.dialect.MySQLDialect":
			return Database.MYSQL;
		default:
			throw new IllegalStateException("Cannot resolve database to resolve jpaVendorAdapter: Uknown hibernate dialect!");
		}
	}
}
