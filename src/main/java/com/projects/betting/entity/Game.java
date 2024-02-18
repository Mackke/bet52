package com.projects.betting.entity;


import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="game")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private int couponIndex;

    private String prediction;

    @ManyToOne
    @JoinColumn(name = "game_coupon")
    private GameCoupon gameCoupon;

    public Game(String name, int couponIndex, String prediction, GameCoupon gameCoupon) {
        this.id = null;
        this.name = name;
        this.couponIndex = couponIndex;
        this.prediction = prediction;
        this.gameCoupon = gameCoupon;
    }
}
