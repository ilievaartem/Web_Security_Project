package com.ilievaartem.gamemarket.repository;

import com.ilievaartem.gamemarket.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}

