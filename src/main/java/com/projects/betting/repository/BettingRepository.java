package com.projects.betting.repository;

import java.util.UUID;

import com.projects.betting.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BettingRepository extends JpaRepository<Game, UUID> {
}
