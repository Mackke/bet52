package com.projects.betting.request;


import lombok.Data;


@Data
public class BettingPixRequest {
    private int totalGames;
    private int homePrediction;
    private int drawPrediction;
    private int AwayPrediction;
}
