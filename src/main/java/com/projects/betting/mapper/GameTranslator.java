package com.projects.betting.mapper;

import java.util.List;

import com.projects.betting.dto.GameDto;
import com.projects.betting.entity.Game;


public class GameTranslator {

    public GameTranslator() {
    }

    public static GameDto gameToGameDto(Game game) {
        return GameDto.builder()
                .id(game.getId())
                .name(game.getName())
                .prediction(game.getPrediction())
                .build();
    }

    public static List<GameDto> gamesToGameDtos(List<Game> games) {
        return games.stream().map(game -> new GameDto(game.getId(), game.getCouponIndex(), game.getName(), game.getPrediction())).toList();
    }
}
