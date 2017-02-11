package com.javangarda.fantacalcio.user.application.internal;

import com.javangarda.fantacalcio.user.application.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends Repository<User, String> {

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email =:email")
	Optional<User> findByEmail(@Param("email") String email);

	@Query("SELECT u FROM User u WHERE u.confirmEmailToken =:token")
	Optional<User> findByConfirmEmailToken(@Param("token") String token);

	@Query("SELECT u FROM User u WHERE u.resetPasswordToken =:token")
	Optional<User> findByResetPasswordToken(@Param("token") String token);

	@Query("SELECT COUNT(u) FROM User u WHERE u.confirmEmailToken=:token")
	int countUserWithConfirmEmailToken(@Param("token") String token);

	@Query("SELECT COUNT(u) FROM User u WHERE u.resetPasswordToken=:token")
	int countUserWithResetPasswordToken(@Param("token") String token);

	Optional<User> findOne(String id);

	void save(User u);
}
