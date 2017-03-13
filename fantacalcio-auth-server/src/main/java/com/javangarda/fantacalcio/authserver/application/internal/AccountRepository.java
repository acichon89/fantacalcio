package com.javangarda.fantacalcio.authserver.application.internal;

import com.javangarda.fantacalcio.authserver.application.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface AccountRepository extends Repository<Account, String> {

    @Query("SELECT a FROM Account a LEFT JOIN FETCH a.roles WHERE a.email =:email")
    Optional<Account> findByEmail(@Param("email") String email);
}
