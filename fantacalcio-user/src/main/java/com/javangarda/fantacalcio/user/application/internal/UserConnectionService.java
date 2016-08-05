package com.javangarda.fantacalcio.user.application.internal;

import org.springframework.social.connect.Connection;

public interface UserConnectionService {

	void saveUserConnection(String email, Connection connection);
}
