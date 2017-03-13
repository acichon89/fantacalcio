package com.javangarda.fantacalcio.authserver.application.internal.impl;

import com.javangarda.fantacalcio.authserver.application.data.FantaCalcioAccount;
import com.javangarda.fantacalcio.authserver.application.internal.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class QueryDrivenUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByEmail(username).map(FantaCalcioAccount::new).orElseThrow(()-> new UsernameNotFoundException("Account with email "+username+" not found."));
    }
}
