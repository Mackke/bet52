package com.projects.betting.repository;

import java.util.UUID;

import com.projects.betting.entity.Game;
import com.projects.betting.entity.GameCoupon;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GameCouponRepository extends JpaRepository<GameCoupon, UUID> {
}
