package com.projects.betting.mapper;

import com.projects.betting.dto.GameCouponDto;
import com.projects.betting.entity.GameCoupon;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class GameCouponMapper {


    public static GameCouponDto gameToGameCouponDto(GameCoupon gameCoupon) {
        return new GameCouponDto(gameCoupon.getId(), gameCoupon.getCouponName());
    }
}
