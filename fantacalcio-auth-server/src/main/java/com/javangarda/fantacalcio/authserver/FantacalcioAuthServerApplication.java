package com.javangarda.fantacalcio.authserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableResourceServer
@EnableDiscoveryClient
public class FantacalcioAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FantacalcioAuthServerApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService(){
        JdbcDaoImpl dao = new JdbcDaoImpl(){
            @Override
            protected List<UserDetails> loadUsersByUsername(String username) {
                return getJdbcTemplate().query("select email,password, status from users " + "where email = ?",
                        new String[] { username }, new RowMapper<UserDetails>() {
                            public UserDetails mapRow(ResultSet rs, int rowNum)
                                    throws SQLException {
                                String username = rs.getString(1);
                                String password = rs.getString(2);
                                boolean enabled = rs.getString(3).equalsIgnoreCase("CONFIRMED");
                                return new User(username, password, enabled, enabled, enabled, enabled,
                                        AuthorityUtils.NO_AUTHORITIES);
                            }

                        });
            }
        };
        dao.setDataSource(dataSource);
        dao.setEnableGroups(false);
        dao.setAuthoritiesByUsernameQuery("select user.email,rel.role "
                + "from users_roles_rel rel left join users user on user.id = rel.user_id where email = ?");
        return dao;
    }

}
