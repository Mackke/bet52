package com.projects.betting;

import lombok.Data;


@Data
public class GameBaseDto {
    String matchName;
    String prediction; // "1", "X", or "2"

    public GameBaseDto(String matchName) {
        this.matchName = matchName;
    }
}
