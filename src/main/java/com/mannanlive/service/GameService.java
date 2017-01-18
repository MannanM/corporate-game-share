package com.mannanlive.service;

import com.mannanlive.entity.GameEntity;
import com.mannanlive.model.Game;
import com.mannanlive.model.Genres;
import com.mannanlive.repository.GameRepository;
import com.mannanlive.translator.GameTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class GameService {
    @Autowired
    private GameRepository repository;

    @Autowired
    private GameTranslator translator;

    public Game findById(long gameId) {
        GameEntity result = repository.findOne(gameId);
        if (result == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return translator.translate(result);
    }

    public Genres listAllGenres(int pageNumber, int pageSize) {
        int safeGetPageSize = pageSize < 1 ? Integer.MAX_VALUE : pageSize;
        return new Genres(repository.findAllGenres(new PageRequest(pageNumber, safeGetPageSize)));
    }
}
