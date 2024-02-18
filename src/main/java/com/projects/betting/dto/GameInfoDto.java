package com.projects.betting.dto;


import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameInfoDto {
    private UUID id;
    private String competition;
    private String name;
    private double homeOdds;
    private double drawOdds;
    private double awayOdds;
}
