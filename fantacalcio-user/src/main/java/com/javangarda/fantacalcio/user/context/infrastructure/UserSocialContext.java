package com.javangarda.fantacalcio.user.context.infrastructure;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import com.javangarda.fantacalcio.user.application.internal.SocialConnectionResolver;
import com.javangarda.fantacalcio.user.application.internal.UserRepository;
import com.javangarda.fantacalcio.user.application.internal.impl.OauthApiSocialConnectionResolver;


@Configuration
@EnableSocial
public class UserSocialContext implements SocialConfigurer {

	@Autowired
	private DataSource dataSource;
    @Autowired
    private ConnectionSignUp connectionSignUp;
    @Autowired
    private UserRepository userRepository;

	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment env) {
		FacebookConnectionFactory fcf = new FacebookConnectionFactory(env.getProperty("facebook.appKey"), env.getProperty("facebook.appSecret"));
		fcf.setScope("email, public_profile");
		connectionFactoryConfigurer.addConnectionFactory(fcf);
		
		GoogleConnectionFactory gcf = new GoogleConnectionFactory(env.getProperty("googleplus.appKey"), env.getProperty("googleplus.appSecret"));
		gcf.setScope("email profile");
		connectionFactoryConfigurer.addConnectionFactory(gcf);
	}

	@Override
	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}

	@Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repo = new JdbcUsersConnectionRepository(
                dataSource,
                connectionFactoryLocator,
                /**
                 * The TextEncryptor object encrypts the authorization details of the connection. In
                 * our example, the authorization details are stored as plain text.
                 * DO NOT USE THIS IN PRODUCTION.
                 */
                Encryptors.noOpText()
        );
        repo.setConnectionSignUp(connectionSignUp);
        return repo;
    }
	
	@Bean
	public SocialConnectionResolver socialConnectionResolver(){
		return new OauthApiSocialConnectionResolver();
	}
}
