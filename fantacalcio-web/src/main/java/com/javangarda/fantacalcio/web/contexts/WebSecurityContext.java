package com.javangarda.fantacalcio.web.contexts;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;


@Configuration
@EnableWebSecurity
public class WebSecurityContext extends WebSecurityConfigurerAdapter {

	    @Autowired
	    private DataSource dataSource;
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    @Autowired
	    private UserDetailsService userDetailsService;

	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        web
	                //Spring Security ignores request to static resources such as CSS or JS files.
	                .ignoring()
	                    .antMatchers("/static/**");
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                //Configures form login
	                .formLogin()
	                    .loginPage("/login")
	                    .loginProcessingUrl("/login/authenticate")
	                    .failureUrl("/login?error=bad_credentials")
	                //Configures the logout function
	                .and()
	                    .logout()
	                        .deleteCookies("JSESSIONID")
	                        .logoutUrl("/logout")
	                        .logoutSuccessUrl("/login")
	                //Configures url based authorization
	                .and()
	                    .authorizeRequests()
	                        //Anyone can access the urls
	                        .antMatchers(
	                                "/auth/**",
	                                "/login",
	                                "/signup/**",
	                                "/user/register/**"
	                        ).permitAll()
	                        //The rest of the our application is protected.
	                        .antMatchers("/**").hasRole("USER")
	                //Adds the SocialAuthenticationFilter to Spring Security's filter chain.
	                .and()
	                    .apply(new SpringSocialConfigurer())
	                .and()
	                	.rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(1209600);
	    }

	    /**
	     * Configures the authentication manager bean which processes authentication
	     * requests.
	     */
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	                .userDetailsService(userDetailsService)
	                .passwordEncoder(passwordEncoder);
	    }
	    
	    @Bean
		public PersistentTokenRepository persistentTokenRepository() {
			JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
			db.setDataSource(dataSource);
			return db;
		}


}
