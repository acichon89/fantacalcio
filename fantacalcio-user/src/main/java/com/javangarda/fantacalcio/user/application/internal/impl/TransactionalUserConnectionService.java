package com.javangarda.fantacalcio.user.application.internal.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.internal.UserConnectionService;

@Component
@Transactional(rollbackOn=Exception.class)
public class TransactionalUserConnectionService implements UserConnectionService {

	@Autowired
	private UsersConnectionRepository usersConnectionRepository;
	
	@Override
	public void saveUserConnection(String email, Connection connection) {
		usersConnectionRepository.createConnectionRepository(email).addConnection(connection);
	}

}
