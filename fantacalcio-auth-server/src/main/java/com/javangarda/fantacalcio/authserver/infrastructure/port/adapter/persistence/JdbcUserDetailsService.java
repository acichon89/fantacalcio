package com.javangarda.fantacalcio.authserver.infrastructure.port.adapter.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Component
public class JdbcUserDetailsService extends JdbcDaoImpl {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void init() {
        setEnableGroups(false);
        setDataSource(dataSource);
        setUsersByUsernameQuery("select email,password, status from users where email = ?");
        setAuthoritiesByUsernameQuery("select user.email,rel.role from users_roles_rel rel left join users user on user.id = rel.user_id where email = ?");
    }

    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {
        return getJdbcTemplate().query(getUsersByUsernameQuery(),
                new String[] { username },(rs, rowNum) -> {
                        String login = rs.getString(1);
                        String password = rs.getString(2);
                        boolean enabled = rs.getString(3).equalsIgnoreCase("CONFIRMED");
                        return new User(login, password, enabled, enabled, enabled, enabled, AuthorityUtils.NO_AUTHORITIES);
                });
    }

}