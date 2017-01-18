package com.mannanlive.controller;

import com.mannanlive.model.Game;
import com.mannanlive.model.Genres;
import com.mannanlive.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/games", method = RequestMethod.GET, produces = "application/vnd.api+json")
public class GameController {

    @Autowired
    private GameService service;

    @RequestMapping(path = "/{gameId}")
    public Game getGameId(@PathVariable Long gameId) {
        return service.findById(gameId);
    }

    @RequestMapping(path = "/genres")
    public Genres getGameGenres(@RequestParam(required = false, defaultValue = "0") int pageNumber,
                                @RequestParam(required = false, defaultValue = "0") int pageSize) {
        return service.listAllGenres(pageNumber, pageSize);
    }
}
