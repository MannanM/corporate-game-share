package com.mannanlive.service;

import com.mannanlive.entity.GameEntity;
import com.mannanlive.model.console.Consoles;
import com.mannanlive.model.game.Game;
import com.mannanlive.model.genre.Genres;
import com.mannanlive.repository.ConsoleRepository;
import com.mannanlive.repository.GameRepository;
import com.mannanlive.translator.ConsoleTranslator;
import com.mannanlive.translator.GameTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import static java.util.stream.Collectors.toList;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ConsoleRepository consoleRepository;

    @Autowired
    private GameTranslator gameTranslator;

    @Autowired
    private ConsoleTranslator consoleTranslator;

    public Game findById(long gameId) {
        GameEntity result = gameRepository.findOne(gameId);
        if (result == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return gameTranslator.translate(result);
    }

    public Genres listAllGenres(int pageNumber, int pageSize) {
        int safeGetPageSize = pageSize < 1 ? Integer.MAX_VALUE : pageSize;
        return new Genres(gameRepository.findAllGenres(new PageRequest(pageNumber, safeGetPageSize)));
    }

    public Consoles listAllConsoles() {
        return new Consoles(consoleRepository.findAll()
                .stream()
                .map(consoleEntity -> consoleTranslator.translate(consoleEntity))
                .collect(toList()));
    }
}
