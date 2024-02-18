package com.projects.betting.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@AllArgsConstructor
public class PixCouponDto {
    private Map<String, String> coupon;

}
