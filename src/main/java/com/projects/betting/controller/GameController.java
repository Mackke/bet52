package com.projects.betting.controller;


import com.projects.betting.dto.GameResponse;
import com.projects.betting.service.GameService;
import com.projects.betting.service.RestTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping()
    ResponseEntity<GameResponse> fetchData() {
        return new ResponseEntity<>(new GameResponse(gameService.getGames()), HttpStatus.OK);
    }
}
