package com.javangarda.fantacalcio.user.infrastructure.port.adapter.http.secure;

import com.javangarda.fantacalcio.user.application.data.FantaCalcioUser;
import com.javangarda.fantacalcio.user.application.internal.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CurrentUserProvider {

    @Autowired
    private UserRepository userRepository;

    public Optional<FantaCalcioUser> getCurrentUser() {
        return userRepository.findByEmail("acichon89@gmail.com").map(FantaCalcioUser::new);
    }
}
