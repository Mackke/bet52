package com.projects.betting.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BetPredictionRequest {
    private String coupon;
    private List<BetDto> betDto;
}
