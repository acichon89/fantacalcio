package com.javangarda.fantacalcio.user;

import java.util.Arrays;
import java.util.concurrent.Executor;

import javax.sql.DataSource;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.javangarda.fantacalcio.user.application.internal.impl.QueryDrivenUserDetailsService;

@SpringBootApplication
@EnableJpaAuditing
public class FantacalcioUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(FantacalcioUserApplication.class, args);
	}
	
	@EnableIntegration
	@IntegrationComponentScan(basePackages={"com.javangarda.fantacalcio.user"})
	public static class IntegrationConfig implements AsyncConfigurer {

		@Bean
		public MessageChannel userRegisteredChannel() {
			return new PublishSubscribeChannel(getAsyncExecutor());
		}

		@Bean
		public MessageChannel createActivationEmailTokenChannel() {
			return new PublishSubscribeChannel(getAsyncExecutor());
		}

		@Bean
		public MessageChannel sendingConfirmationEmailChannel() {
			return new PublishSubscribeChannel(getAsyncExecutor());
		}
		
		@Override
		public Executor getAsyncExecutor() {
			ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
			executor.setCorePoolSize(50);
			executor.setMaxPoolSize(50);
			executor.setQueueCapacity(50);
			executor.initialize();
			return executor;
		}

		@Override
		public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
			return new SimpleAsyncUncaughtExceptionHandler();
		}
		
}
	
	@Configuration
	public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private QueryDrivenUserDetailsService userDetailsService;
		
		@Bean
		public PasswordEncoder passwordEncoder(){
			return new BCryptPasswordEncoder(10);
		}
		
	    @Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		}

		@Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @Override
	    protected void configure(final HttpSecurity http) throws Exception {
	        // @formatter:off
			http.authorizeRequests().antMatchers("/login", "/user/**").permitAll().anyRequest().authenticated().and().formLogin()
					.permitAll();
			http.csrf().disable();
			// @formatter:on
	    }

	}
	
	@Configuration
	//@PropertySource({ "classpath:persistence.properties" })
	@EnableAuthorizationServer
	public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;
		
		@Autowired
		private TokenEnhancer tokenEnhancer;
		
		@Autowired
		private DataSource dataSource;

		@Override
		public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
			oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
		}

		@Override
		public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {// @formatter:off
			clients.jdbc(dataSource)
					//inMemory()
					.withClient("sampleClientId").authorizedGrantTypes("implicit")
					.scopes("read", "write", "foo", "bar").autoApprove(false).accessTokenValiditySeconds(3600)

					.and().withClient("fooClientIdPassword").secret("secret")
					.authorizedGrantTypes("password", "authorization_code", "refresh_token").scopes("foo", "read", "write")
					.accessTokenValiditySeconds(3600) // 1 hour
					.refreshTokenValiditySeconds(2592000) // 30 days

					.and().withClient("barClientIdPassword").secret("secret")
					.authorizedGrantTypes("password", "authorization_code", "refresh_token").scopes("bar", "read", "write")
					.accessTokenValiditySeconds(3600) // 1 hour
					.refreshTokenValiditySeconds(2592000) // 30 days
					;
		} // @formatter:on

		@Override
		public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			// @formatter:off
			final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
			tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer, accessTokenConverter()));
			endpoints.tokenStore(tokenStore())
					// .accessTokenConverter(accessTokenConverter())
					.tokenEnhancer(tokenEnhancerChain).authenticationManager(authenticationManager);
			// @formatter:on
		}

		@Bean
		public JwtAccessTokenConverter accessTokenConverter() {
			final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
			converter.setSigningKey("123");
			/*final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"),
					"mypass".toCharArray());
			converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));*/
			return converter;
		}

		@Bean
		@Primary
		public DefaultTokenServices tokenServices() {
			final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
			defaultTokenServices.setTokenStore(tokenStore());
			defaultTokenServices.setSupportRefreshToken(true);
			return defaultTokenServices;
		}


		// JDBC token store configuration

		/*@Bean
		public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
			final DataSourceInitializer initializer = new DataSourceInitializer();
			initializer.setDataSource(dataSource);
			initializer.setDatabasePopulator(databasePopulator());
			return initializer;
		}*/

		/*private DatabasePopulator databasePopulator() {
			final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
			populator.addScript(schemaScript);
			return populator;
		}*/

		/*@Bean
		public DataSource dataSource() {
			final DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
			dataSource.setUrl(env.getProperty("jdbc.url"));
			dataSource.setUsername(env.getProperty("jdbc.user"));
			dataSource.setPassword(env.getProperty("jdbc.pass"));
			return dataSource;
		}*/

		@Bean
		public TokenStore tokenStore() {
			return new JdbcTokenStore(dataSource);
		}
		
		/*@Bean
		public TokenStore tokenStore() {
			return new JwtTokenStore(accessTokenConverter());
		}*/

	}

}
