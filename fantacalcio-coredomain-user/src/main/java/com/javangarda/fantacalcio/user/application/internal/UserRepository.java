package com.javangarda.fantacalcio.user.application.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javangarda.fantacalcio.user.application.model.User;

public interface UserRepository extends JpaRepository<User, String>{

	@Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email =:email")
	User findByEmail(@Param("email") String email);
	
	@Query("SELECT u FROM User u WHERE u.confirmEmailToken =:token")
	User findByConfirmEmailToken(@Param("token") String token);

	@Query("SELECT COUNT(u) FROM User u WHERE u.email=:email")
	int countUserWithEmail(@Param("email") String email);
	
	@Query("SELECT COUNT(u) FROM User u WHERE u.confirmEmailToken=:token")
	int countUserWithConfirmEmailToken(@Param("token") String token);
}
