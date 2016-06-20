package com.javangarda.fantacalcio.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javangarda.fantacalcio.user.domain.model.User;

public interface UserRepository extends JpaRepository<User, String>{

	User findByEmail(String email);
}
