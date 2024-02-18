package com.projects.betting.dto;


import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prediction {
    int game;
    String predictionBet;
    String hedging;


    @JsonProperty(value = "finalPrediction")
    public String getResult() {
        hedging = hedging == null ? "" : hedging;
        return predictionBet + hedging;
    }

    public void randomizeHedging(List<String> possibleResults) {
        Random random = new Random();
        String newPrediction = possibleResults.get(random.nextInt(3));

        if(predictionBet.equals(newPrediction)) {
            randomizeHedging(possibleResults);
        } else {
            hedging = newPrediction;
        }
    }

}
