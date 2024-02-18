package com.projects.betting.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredictionDto {
    private GameCouponDto coupon;
    private List<GameDto> gameDtos;
}
