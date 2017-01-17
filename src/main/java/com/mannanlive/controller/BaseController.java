package com.mannanlive.controller;

import com.mannanlive.entity.Game;
import com.mannanlive.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1")
public class BaseController {

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping(path = "/games/{gameId}")
    public Game getGameId(@PathVariable("gameId") Long gameId) {
        return gameRepository.findOne(gameId);
    }
}
