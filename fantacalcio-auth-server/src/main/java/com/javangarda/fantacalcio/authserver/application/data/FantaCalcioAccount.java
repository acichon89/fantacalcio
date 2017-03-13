package com.javangarda.fantacalcio.authserver.application.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.javangarda.fantacalcio.authserver.application.model.Account;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.stream.Collectors;

@JsonIgnoreProperties(value = {"password"})
public class FantaCalcioAccount extends org.springframework.security.core.userdetails.User {

    private String id;

    public FantaCalcioAccount(Account account) {
        super(account.getEmail(),account.getPassword(), account.isAllowed(), account.isAllowed(), account.isAllowed(), account.isAllowed(),
                account.getRoles().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toCollection(HashSet::new)));
        this.id=account.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        FantaCalcioAccount that = (FantaCalcioAccount) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }
}
