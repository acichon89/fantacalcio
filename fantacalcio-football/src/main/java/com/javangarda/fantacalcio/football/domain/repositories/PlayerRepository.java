package com.javangarda.fantacalcio.football.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javangarda.fantacalcio.football.domain.model.Player;

public interface PlayerRepository extends JpaRepository<Player, String>{

}
