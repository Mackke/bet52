package com.projects.betting.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiplePixRequest {
    private List<GamePredictionDto> gamePredictions;
    private int gamesToBeHedged;
}
