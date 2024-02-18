package com.projects.betting.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneratePredictionRequest {
    private int games;
    private int halfHedges;
    private int coupons;
}
