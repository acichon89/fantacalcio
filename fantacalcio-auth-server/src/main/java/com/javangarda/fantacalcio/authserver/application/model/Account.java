package com.javangarda.fantacalcio.authserver.application.model;

import com.javangarda.fantacalcio.commons.entities.DefaultEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class Account extends DefaultEntity<String> {

    @Getter
    private String email;
    @Getter
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @ElementCollection
    @CollectionTable(name="users_roles_rel", joinColumns=@JoinColumn(name="user_id"))
    @Column(name="role")
    @Getter
    private Set<String> roles = new HashSet<String>();

    public Account() {
        super();
    }

    public boolean isAllowed(){
        return this.status.isAllowed();
    }
}
