package com.projects.betting.service;

import java.util.List;
import java.util.UUID;

import com.projects.betting.dto.GameInfoDto;
import org.springframework.stereotype.Service;


@Service
public class GameService {

    public List<GameInfoDto> getGames() {
        List<GameInfoDto> dtos = List.of(
                new GameInfoDto(UUID.randomUUID(), "Premier League", "Bolton - West Bromwhich", 2.23, 3.1, 2.54),
                new GameInfoDto(UUID.randomUUID(), "Premier League", "Manchester City - West Ham", 2.1, 4.1, 4.54),
                new GameInfoDto(UUID.randomUUID(), "Premier League", "Liverpool - Manchester United", 2.2, 3.4, 4.54),
                new GameInfoDto(UUID.randomUUID(), "Premier League", "Chelsea - Luton", 2.21, 3.5, 4.54),
                new GameInfoDto(UUID.randomUUID(), "Premier League", "Sheffield United - Crystal Palace", 2.3, 3.0, 4.54),
                new GameInfoDto(UUID.randomUUID(), "Premier League", "Watford - Leicester", 2.9, 2.5, 3.54),
                new GameInfoDto(UUID.randomUUID(), "Premier League", "Hull - Ipswich", 2.4, 3.0, 4.54),
                new GameInfoDto(UUID.randomUUID(), "Premier League", "Tottenham - Wolverhampton", 2.22, 3.1, 1.54),
                new GameInfoDto(UUID.randomUUID(), "Premier League", "Arsenal - Fulham", 4.23, 3.1, 3.54),
                new GameInfoDto(UUID.randomUUID(), "La Liga", "Real Madrid - Getafe", 2.1, 3.1, 4.54),
                new GameInfoDto(UUID.randomUUID(), "La Liga", "Villareal - Girona", 2.32, 3.1, 2.4),
                new GameInfoDto(UUID.randomUUID(), "La Liga", "Barcelona - Atletico Madrid", 2.56, 3.1, 2.1),
                new GameInfoDto(UUID.randomUUID(), "La Liga", "Mallorca - Celta Vigo", 2.2, 3.1, 2.3),
                new GameInfoDto(UUID.randomUUID(), "La Liga", "Granada - Sevilla", 3.3, 3.1, 2.0),
                new GameInfoDto(UUID.randomUUID(), "La Liga", "Valencia - Real Betis", 3.23, 3.1, 2.9)
        );

        return dtos;
    }
}
