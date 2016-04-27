package com.javangarda.fantacalcio.football.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javangarda.fantacalcio.football.domain.model.Club;

public interface ClubRepository extends JpaRepository<Club, String> {

	@Query("select count(c) from Club c where c.name = :name")
	int countByName(@Param("name") String name);
	
	@Query("select count(c) from Club c where c.name = :name and c.id != :id")
	int countByNameAndNotId(@Param("name") String name, @Param("id") String id);
}
