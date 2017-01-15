package com.javangarda.fantacalcio.user.application.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.javangarda.fantacalcio.user.application.model.User;

import java.util.Optional;

public interface UserRepository extends Repository<User, String> {

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email =:email")
	Optional<User> findByEmail(@Param("email") String email);

	@Query("SELECT u FROM User u WHERE u.confirmEmailToken =:token")
	Optional<User> findByConfirmEmailToken(@Param("token") String token);

	@Query("SELECT COUNT(u) FROM User u WHERE u.confirmEmailToken=:token")
	int countUserWithConfirmEmailToken(@Param("token") String token);

	Optional<User> findOne(String id);

	void save(User u);

	@Query("SELECT COUNT(u) FROM User u WHERE u.email=:email and u.password=:password")
	int countUserWithPasswordAndEmail(@Param("password") String password, @Param("email") String email);
}
