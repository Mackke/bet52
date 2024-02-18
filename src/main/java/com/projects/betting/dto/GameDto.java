package com.projects.betting.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class GameDto {
    private UUID id;

    private int gameIndex;

    private String name;

    private String prediction;

    public GameDto() {
    }

    public GameDto(UUID id, int gameIndex, String name, String prediction) {
        this.id = id;
        this.gameIndex = gameIndex;
        this.name = name;
        this.prediction = prediction;
    }

    public GameDto(int gameIndex, String name, String prediction) {
        this.id = UUID.randomUUID();
        this.gameIndex = gameIndex;
        this.name = name;
        this.prediction = prediction;
    }
}
