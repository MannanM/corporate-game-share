package com.mannanlive.controller;

import com.mannanlive.model.console.Consoles;
import com.mannanlive.model.game.Game;
import com.mannanlive.model.genre.Genres;
import com.mannanlive.service.GameService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1", method = RequestMethod.GET, produces = "application/vnd.api+json")
public class GameController {

    @Autowired
    private GameService service;

    @RequestMapping(path = "/games/{gameId}")
    @ApiOperation(value = "View details about a game")
    public Game getGameId(@PathVariable Long gameId) {
        return service.findById(gameId);
    }

    @RequestMapping(path = "/games/genres")
    @ApiOperation(value = "View all known game genres")
    public Genres getGameGenres(@RequestParam(required = false, defaultValue = "0") int pageNumber,
                                @RequestParam(required = false, defaultValue = "0") int pageSize) {
        return service.listAllGenres(pageNumber, pageSize);
    }

    @RequestMapping(path = "/consoles")
    @ApiOperation(value = "View all consoles")
    public Consoles getConsoles() {
        return service.listAllConsoles();
    }
}
