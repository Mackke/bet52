package com.projects.betting.entity;


import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "game_coupon")
@Getter
@Setter
@NoArgsConstructor
public class GameCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = UUID.randomUUID();

    @Column(name = "coupon_name")
    private String couponName;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Builder
    public GameCoupon(UUID id, String couponName, Instant createdAt, Instant updatedAt) {
        this.id = (id != null) ? id : UUID.randomUUID();
        this.couponName = couponName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
